package com.boky.PFE.restController;

import com.boky.PFE.entite.Admin;
import com.boky.PFE.entite.Client;
import com.boky.PFE.repository.ClientRepository;
import com.boky.PFE.service.ClientService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/Client")
public class ClientResController
{
    @Autowired
    ClientRepository clientRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @RequestMapping(method = RequestMethod.POST )
    ResponseEntity<?> AjouterClient (@RequestBody Client client)
    {
        HashMap<String, Object> response = new HashMap<>();
        if(clientRepository.existsByEmail(client.getEmail())){
            response.put("message", "email exist deja !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }else{
            client.setMdp(this.bCryptPasswordEncoder.encode(client.getMdp()));
            Client savedUser = clientRepository.save(client);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);}

    }
    @Autowired
    ClientService clientService;
    @RequestMapping(method = RequestMethod.GET)
    public List<Client> AfficherClient()
    {
        return clientService.AfficherClient();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE )

    public void SupprimerClient(@PathVariable("id") Long id){
        clientService.SupprimerClient(id);

    }
    @PostMapping("/Login")
    public ResponseEntity<Map<String, Object>> loginClient(@RequestBody Client client) {
        System.out.println("in login-client"+client);
        HashMap<String, Object> response = new HashMap<>();

        Client userFromDB = clientRepository.findClientByEmail(client.getEmail());
        System.out.println("userFromDB+client"+userFromDB);
        if (userFromDB == null) {
            response.put("message", "Client not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            boolean compare = this.bCryptPasswordEncoder.matches(client.getMdp(), userFromDB.getMdp());
            System.out.println("compare"+compare);
            if (!compare) {
                response.put("message", "admin not found !");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }else
            {
                String token = Jwts.builder()
                        .claim("data", userFromDB)
                        .signWith(SignatureAlgorithm.HS256, "SECRET")
                        .compact();
                response.put("token", token);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }

        }
    }
    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public Client ModifierClient(@PathVariable("id")Long id, @RequestBody Client client){
        client.setMdp(this.bCryptPasswordEncoder.encode(client.getMdp()));
        Client newClient = clientService.ModifierClient(client);
        return newClient;
    }
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Optional<Client> getClientById(@PathVariable("id") long id){

        Optional<Client> client = clientService.getClientById(id);
        return client;
    }
}

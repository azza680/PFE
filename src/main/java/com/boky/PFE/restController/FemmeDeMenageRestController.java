package com.boky.PFE.restController;

import com.boky.PFE.entite.Admin;
import com.boky.PFE.entite.Contact;
import com.boky.PFE.entite.FemmeDeMenage;
import com.boky.PFE.repository.AdminRepository;
import com.boky.PFE.repository.FemmeDMRepository;
import com.boky.PFE.service.AdminService;
import com.boky.PFE.service.FemmeDMService;
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
@RequestMapping(value = "/FemmeDeMenage")
public class FemmeDeMenageRestController
{
    @Autowired
    FemmeDMRepository femmeDMRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @RequestMapping(method = RequestMethod.POST )
    ResponseEntity<?> AjouterFemmeDM (@RequestBody FemmeDeMenage femmeDeMenage)
    {
        HashMap<String, Object> response = new HashMap<>();
        if(femmeDMRepository.existsByEmail(femmeDeMenage.getEmail())){
            response.put("message", "email exist deja !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }else{
            femmeDeMenage.setMdp(this.bCryptPasswordEncoder.encode(femmeDeMenage.getMdp()));
            FemmeDeMenage savedUser = femmeDMRepository.save(femmeDeMenage);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);}

    }
    @Autowired
    FemmeDMService femmeDMService;
    @RequestMapping(method = RequestMethod.GET)
    public List<FemmeDeMenage> AfficherFemmeDM()
    {
        return femmeDMService.AfficherFemmeDM();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE )

    public void SupprimerFemmeDM(@PathVariable("id") Long id){
        femmeDMService.SupprimerFemmeDM(id);

    }
    @PostMapping("/Login")
    public ResponseEntity<Map<String, Object>> loginFemmeDM(@RequestBody FemmeDeMenage femmeDeMenage) {
        System.out.println("in login-femmeDeMenage"+femmeDeMenage);
        HashMap<String, Object> response = new HashMap<>();

        FemmeDeMenage userFromDB = femmeDMRepository.findFemmeDMByEmail(femmeDeMenage.getEmail());
        System.out.println("userFromDB+femmeDeMenage"+userFromDB);
        if (userFromDB == null) {
            response.put("message", "femmeDeMenage not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            boolean compare = this.bCryptPasswordEncoder.matches(femmeDeMenage.getMdp(), userFromDB.getMdp());
            System.out.println("compare"+compare);
            if (!compare) {
                response.put("message", "femmeDeMenage not found !");
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
    public FemmeDeMenage ModifierFemmeDM(@PathVariable("id")Long id, @RequestBody FemmeDeMenage femmeDeMenage){
        femmeDeMenage.setMdp(this.bCryptPasswordEncoder.encode(femmeDeMenage.getMdp()));
        FemmeDeMenage  newFemmeDM = femmeDMService.ModifierFemmeDM(femmeDeMenage);
        return newFemmeDM;
    }
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Optional<FemmeDeMenage> getFemmeDMById(@PathVariable("id") long id){

        Optional<FemmeDeMenage> femmeDeMenage = femmeDMService.getFemmeDMById(id);
        return femmeDeMenage;
    }
}

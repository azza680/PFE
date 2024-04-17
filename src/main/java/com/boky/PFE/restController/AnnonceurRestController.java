package com.boky.PFE.restController;
import com.boky.PFE.entite.Admin;
import com.boky.PFE.entite.Annonceur;
import com.boky.PFE.repository.AnnonceurRepository;
import com.boky.PFE.service.AnnonceurService;
import com.boky.PFE.service.EmailService;
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
@RequestMapping(value = "/Annonceur")
public class AnnonceurRestController
{
    @Autowired
    AnnonceurRepository annonceurRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @RequestMapping(method = RequestMethod.POST )
    ResponseEntity<?> AjouterAnnonceur (@RequestBody Annonceur annonceur)
    {
        HashMap<String, Object> response = new HashMap<>();
        if(annonceurRepository.existsByEmail(annonceur.getEmail())){
            response.put("message", "email exist deja !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }else{
            annonceur.setMdp(this.bCryptPasswordEncoder.encode(annonceur.getMdp()));
            Annonceur savedUser = annonceurRepository.save(annonceur);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);}

    }
    @Autowired
    AnnonceurService annonceurService;
    @Autowired
    EmailService emailService;
    @RequestMapping(method = RequestMethod.GET)
    public List<Annonceur> AfficherAnnonceur()
    {
        return annonceurService.AfficherAnnonceur();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE )

    public void SupprimerAnnonceur(@PathVariable("id") Long id){
        annonceurService.SupprimerAnnonceur(id);

    }
    @PostMapping("/Login")
    public ResponseEntity<Map<String, Object>> loginAnnonceur(@RequestBody Annonceur annonceur) {
        System.out.println("in login-annonceur"+annonceur);
        HashMap<String, Object> response = new HashMap<>();

        Annonceur userFromDB = annonceurRepository.findAnnonceurByEmail(annonceur.getEmail());
        System.out.println("userFromDB+annonceur"+userFromDB);
        if (userFromDB == null) {
            response.put("message", "Annonceur not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            boolean compare = this.bCryptPasswordEncoder.matches(annonceur.getMdp(), userFromDB.getMdp());
            System.out.println("compare"+compare);
            if (!compare) {
                response.put("message", "annonceur not found !");
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

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Annonceur modifierAnnonceur(@RequestBody Annonceur annonceur, @PathVariable("id") Long id) {
        Annonceur newFournisseur = null;
        if (annonceurRepository.findById(id).isPresent()) { //ken user deja mawjoud
            Annonceur annonceur1 = annonceurRepository.findById(id).get();
            var annonceurId = annonceur.getId();
            var nom = annonceur.getNom();
            var prenom = annonceur.getPrenom();
            var email = annonceur.getEmail();
            var mdp = annonceur.getMdp();
            var tel = annonceur.getTelephone();
            annonceur1.setId(annonceurId);
            annonceur1.setNom(nom);
            annonceur1.setPrenom(prenom);
            annonceur1.setEmail(email);
            annonceur1.setMdp(mdp);
            annonceur1.setTelephone(tel);

//mta3 yjih mail fih l etat
            annonceur.setMdp(this.bCryptPasswordEncoder.encode(annonceur1.getMdp()));
            if (annonceur.isEtat() != annonceur1.isEtat()) {
                //ternary expression
                String etat = annonceur1.isEtat() ? "Bloqué" : "Accepté";
                emailService.SendSimpleMessage(annonceur1.getEmail(), "L'etat de votre compte", "votre compte a été " + etat);
            }
            annonceur1.setEtat(annonceur.isEtat());
            newFournisseur = annonceurRepository.save(annonceur1);
        }
        return newFournisseur;
    }
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Optional<Annonceur> getAnnonceurById(@PathVariable("id") long id){

        Optional<Annonceur> annonceur = annonceurService.getAnnonceurById(id);
        return annonceur;
    }
}

package com.boky.PFE.restController;
import com.boky.PFE.service.ConfirmationTokenService;
import com.boky.PFE.service.EmailUtilisateurService;
import com.boky.PFE.util.NewPassword;
import com.boky.PFE.util.UserCode;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.boky.PFE.entite.Utilisateur;
import com.boky.PFE.repository.UtilisateurRepository;

import com.boky.PFE.service.EmailService;
import com.boky.PFE.service.UtilisateurService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/Utilisateur")
public class UtilisateurRestController {
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    EmailUtilisateurService emailUtilisateurService;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @PostMapping(value = "/register")
    ResponseEntity<?> AjouterUtilisateur (@RequestBody Utilisateur utilisateur)
    {
        return utilisateurService.AjouterUtilisateur(utilisateur);
    }

    @Autowired
    UtilisateurService utilisateurService;
    @Autowired
    EmailService emailService;

    @Autowired
    ConfirmationTokenService confirmationTokenService;
    @RequestMapping(method = RequestMethod.GET)
    public List<Utilisateur> AfficherUtilisateur()
    {
        return utilisateurService.AfficherUtilisateur();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE )

    public void SupprimerUtilisateur(@PathVariable("id") Long id){
        utilisateurService.SupprimerUtilisateur(id);

    }
    @PostMapping("/Login")
    public ResponseEntity<Map<String, Object>> loginUtilisateur(@RequestBody Utilisateur utilisateur) {
        System.out.println("in login-utilisateur"+utilisateur);
        HashMap<String, Object> response = new HashMap<>();
        Utilisateur userFromDB = utilisateurRepository.findUtilisateurByEmail(utilisateur.getEmail());
        System.out.println("userFromDB+utilisateur"+userFromDB);
        if (userFromDB == null)
        {
            response.put("message", "Utilisateur not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        }
        else
        {
            boolean compare = this.bCryptPasswordEncoder.matches(utilisateur.getMdp(), userFromDB.getMdp());
            System.out.println("compare"+compare);
            if (!compare) {

                response.put("message", "Incorrect password !");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            else
            {
                 if (!userFromDB.isEtat()) {
                     response.put("message", "Account is not activated !");
                     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);

                 }
                System.out.println("erreur hna1");
                    String token = Jwts.builder()
                            .claim("data", userFromDB)
                            .signWith(SignatureAlgorithm.HS256, "SECRET")
                            .compact();
                System.out.println("erreur hna");
                    response.put("token", token);
                    return ResponseEntity.status(HttpStatus.OK).body(response);

            }

        }
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Utilisateur ModifierUtilisateur(@RequestBody Utilisateur utilisateur, @PathVariable("id") Long id) {
        Utilisateur newUtilisateur = null;
        if (utilisateurRepository.findById(id).isPresent()) { //ken user deja mawjoud
            Utilisateur utilisateur1 = utilisateurRepository.findById(id).get();
            var utilisateurId = utilisateur.getId();
            var nom = utilisateur.getNom();
            var prenom = utilisateur.getPrenom();
            var email = utilisateur.getEmail();
            var Date_N = utilisateur.getDate_de_naissance();
            var tel = utilisateur.getTelephone();
            var adresse = utilisateur.getAdresse();
            var mdp = utilisateur.getMdp();
            var role = utilisateur.getRole();
            utilisateur1.setId(utilisateurId);
            utilisateur1.setNom(nom);
            utilisateur1.setPrenom(prenom);
            utilisateur1.setEmail(email);
            utilisateur1.setDate_de_naissance(Date_N);
            utilisateur1.setTelephone(tel);
            utilisateur1.setAdresse(adresse);
            utilisateur1.setMdp(mdp);
            utilisateur1.setRole(role);


//mta3 yjih mail fih l etat
            utilisateur.setMdp(this.bCryptPasswordEncoder.encode(utilisateur1.getMdp()));
            if (utilisateur.isEtat() != utilisateur1.isEtat()) {
                //ternary expression
                String etat = utilisateur1.isEtat() ? "Bloqué" : "Accepté";
                emailService.SendSimpleMessage(utilisateur1.getEmail(), "L'etat de votre compte", "votre compte a été " + etat);
            }
            utilisateur1.setEtat(utilisateur.isEtat());
            newUtilisateur = utilisateurRepository.save(utilisateur1);
        }
        return newUtilisateur;
    }
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Optional<Utilisateur> getUtilisateurById(@PathVariable("id") long id){

        Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateurById(id);
        return utilisateur;
    }
    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
        return utilisateurService.ConfirmationEmail(confirmationToken);
    }
    @PostMapping("/{userId}/photo")
    public ResponseEntity<?> uploadProfilePhoto(@PathVariable Long userId, @RequestParam("photo") MultipartFile photo) {
        // Recherche de l'utilisateur par son ID
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(userId);
        if (optionalUtilisateur.isPresent()) {
            Utilisateur utilisateur = optionalUtilisateur.get();
            try {
                // Convertir la photo en tableau de bytes
                byte[] photoBytes = photo.getBytes();
                // Stocker la photo dans l'attribut correspondant de l'entité Utilisateur
                utilisateur.setPhoto(photoBytes);
                // Enregistrer les modifications dans la base de données
                utilisateurRepository.save(utilisateur);
                return ResponseEntity.ok().build();
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/checkEmail")
    public ResponseEntity<Map<String, Object>> resetPasswordEmail(@RequestBody Utilisateur utilisateur){
        System.out.println("email ali mawjoud hawahouuuuuuuuuuu"+utilisateur.getEmail());
        HashMap<String, Object> response = new HashMap<>();
        Utilisateur user = utilisateurRepository.findUtilisateurByEmail(utilisateur.getEmail());

        System.out.println("userFromDB+utilisateur "+user);
        if(user != null){
            String code = UserCode.getCode();

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(utilisateur.getEmail());
            mailMessage.setSubject("Code de réinitialisation de mot de passe");
            mailMessage.setText("Votre code : "+code);
            emailUtilisateurService.sendEmail(mailMessage);

            user.getCode().setCode(code);
            this.utilisateurService.ModifierUtilisateur(user, user.getId());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    // http://localhost:8080/resetPassword
    @PostMapping("/resetPassword")
    public ResponseEntity<Map<String, Object>> resetPassword(@RequestBody NewPassword newPassword){
        HashMap<String, Object> response = new HashMap<>();
        Utilisateur user = utilisateurRepository.findUtilisateurByEmail(newPassword.getEmail());
        if(user != null){
            if(user.getCode().getCode().equals(newPassword.getCode())){
                System.out.println("hatha mdp ali ja"+newPassword.getPassword());
                String newmdp = this.bCryptPasswordEncoder.encode(newPassword.getPassword());
                System.out.println("hatha newmdp"+newmdp);
                user.setMdp(newmdp);
                System.out.println("hatha mdp jdid"+user.getMdp());
                utilisateurService.ModifierUtilisateur(user,user.getId());
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(user.getEmail());
                mailMessage.setSubject("Réinitialisation de mot de passe");
                mailMessage.setText("Votre mot de passe a été changé avec succès !");
                emailUtilisateurService.sendEmail(mailMessage);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}


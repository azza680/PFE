package com.boky.PFE.restController;

import com.boky.PFE.Beans.SaveAnnonce;
import com.boky.PFE.entite.Admin;
import com.boky.PFE.entite.Annonce;
import com.boky.PFE.entite.Client;
import com.boky.PFE.entite.Utilisateur;
import com.boky.PFE.repository.AnnonceRepository;
import com.boky.PFE.repository.ClientRepository;
import com.boky.PFE.service.AnnonceService;
import com.boky.PFE.service.EmailService;
import com.boky.PFE.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/Annonce")
public class AnnonceRestController
{
    
    @Autowired
    AnnonceService annonceService;

    @RequestMapping(method = RequestMethod.POST)
    public Annonce AjouterAnnonce (@RequestBody com.boky.PFE.Beans.SaveAnnonce model) {
        return annonceService.AjouterAnnonce(model);
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<Annonce> AfficherAnnonce()
    {
        return annonceService.AfficherAnnonce();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE )

    public void SupprimerAnnonce(@PathVariable("id") Long id){
        annonceService.SupprimerAnnonce(id);

    }
    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public Annonce ModifierAnnonce(@PathVariable("id")Long id, @RequestBody Annonce annonce){
        Annonce newAnnonce = annonceService.ModifierAnnonce(annonce);
        return newAnnonce;
    }
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Optional<Annonce> getAnnonceById(@PathVariable("id") long id){

        Optional<Annonce> annonce = annonceService.getAnnonceById(id);
        return annonce;
    }
}

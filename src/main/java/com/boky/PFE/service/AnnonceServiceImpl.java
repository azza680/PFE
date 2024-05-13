package com.boky.PFE.service;
import com.boky.PFE.Beans.SaveAnnonce;
import com.boky.PFE.entite.Annonce;
import com.boky.PFE.entite.Reservation;
import com.boky.PFE.entite.Utilisateur;
import com.boky.PFE.repository.AnnonceRepository;
import com.boky.PFE.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
@Service
public class AnnonceServiceImpl implements AnnonceService
{
    @Autowired
    AnnonceRepository annonceRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Override
    public Annonce AjouterAnnonce(SaveAnnonce model) {
        Annonce annonce = SaveAnnonce.toEntity(model);
        System.out.println("idAnnonceur: " + model.getId_annonceur());

        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(model.getId_annonceur());
        if (utilisateurOptional.isPresent()) {
            Utilisateur utilisateur = utilisateurOptional.get();
            annonce.setAnnonceur(utilisateur);
            return annonceRepository.save(annonce);
        } else {
            // Gérer le cas où l'utilisateur n'est pas trouvé
            return null; // Ou lancer une exception, selon le cas
        }
    }


    @Override
    public Annonce ModifierAnnonce(Annonce annonce) {
        return annonceRepository.save(annonce);
    }

    @Override
    public List<Annonce> AfficherAnnonce() {
        return annonceRepository.findAll();
    }

    @Override
    public void SupprimerAnnonce(Long id) {
        annonceRepository.deleteById(id);
    }

    @Override
    public Optional<Annonce> getAnnonceById(Long id) {
        return annonceRepository.findById(id);
    }
    public List<Annonce> listeAnnonceByAnnonceur(Long id) {
        return annonceRepository.findByAnnonceurId(id);
    }
    public Utilisateur UtilisateurByAnnonceur(@PathVariable  Long id) {
        Optional<Annonce> annonce =  annonceRepository.findById(id);
        return annonce.get().getAnnonceur();
    }
}

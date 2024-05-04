package com.boky.PFE.service;
import com.boky.PFE.Beans.SaveAnnonce;
import com.boky.PFE.entite.Annonce;
import com.boky.PFE.entite.Utilisateur;
import com.boky.PFE.repository.AnnonceRepository;
import com.boky.PFE.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Annonce annonce=SaveAnnonce.toEntity(model);
        System.out.println("idUtilisateur"+model.getId_utilisateur());
        Utilisateur utilisateur=utilisateurRepository.findById(model.getId_utilisateur()).get();
        annonce.setUtilisateur(utilisateur);
        return annonceRepository.save(annonce);
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
}

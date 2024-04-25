package com.boky.PFE.service;
import com.boky.PFE.entite.Annonce;
import com.boky.PFE.repository.AnnonceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AnnonceServiceImpl implements AnnonceService
{
    @Autowired
    AnnonceRepository annonceRepository;
    @Override
    public Annonce AjouterAnnonce(Annonce annonce) {
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

package com.boky.PFE.service;

import com.boky.PFE.entite.Annonceur;
import com.boky.PFE.repository.AnnonceurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AnnonceurServiceImpl implements AnnonceurService
{
    @Autowired
    AnnonceurRepository annonceurRepository;
    @Override
    public Annonceur AjouterAnnonceur(Annonceur annonceur) {
        return annonceurRepository.save(annonceur);
    }

    @Override
    public Annonceur ModifierAnnonceur(Annonceur annonceur, long id) {
        return annonceurRepository.save(annonceur);
    }

    @Override
    public List<Annonceur> AfficherAnnonceur() {
        return annonceurRepository.findAll();
    }

    @Override
    public void SupprimerAnnonceur(Long id) {
        annonceurRepository.deleteById(id);
    }

    @Override
    public Optional<Annonceur> getAnnonceurById(Long id) {
        return annonceurRepository.findById(id);
    }
}

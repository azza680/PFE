package com.boky.PFE.service;

import com.boky.PFE.entite.Annonceur;


import java.util.List;
import java.util.Optional;

public interface AnnonceurService
{
    Annonceur AjouterAnnonceur(Annonceur annonceur);
    Annonceur ModifierAnnonceur(Annonceur annonceur, long id);
    List<Annonceur> AfficherAnnonceur();
    void SupprimerAnnonceur (Long id);
    Optional<Annonceur> getAnnonceurById(Long id);
}

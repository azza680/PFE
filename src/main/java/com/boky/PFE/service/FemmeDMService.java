package com.boky.PFE.service;

import com.boky.PFE.entite.Admin;
import com.boky.PFE.entite.FemmeDeMenage;

import java.util.List;
import java.util.Optional;

public interface FemmeDMService
{
    FemmeDeMenage AjouterFemmeDM(FemmeDeMenage femmeDeMenage);
    FemmeDeMenage ModifierFemmeDM(FemmeDeMenage femmeDeMenage);
    List<FemmeDeMenage> AfficherFemmeDM();
    void SupprimerFemmeDM (Long id);
    Optional<FemmeDeMenage> getFemmeDMById(Long id);
}

package com.boky.PFE.service;

import com.boky.PFE.entite.Planification;

import java.util.List;
import java.util.Optional;

public interface PlanificationService
{
    Planification AjouterPlanification(Planification planification);
    Planification ModifierPlanification(Planification planification);
    List<Planification> AfficherPlanification();
    void SupprimerPlanification (Long id);
    Optional<Planification> getPlanificationById(Long id);
}

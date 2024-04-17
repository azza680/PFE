package com.boky.PFE.service;

import com.boky.PFE.entite.Planification;
import com.boky.PFE.repository.PlanificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PlanificationServiceImpl implements PlanificationService
{
    @Autowired
    PlanificationRepository planificationRepository;
    @Override
    public Planification AjouterPlanification(Planification planification)
    {
        return planificationRepository.save(planification);
    }

    @Override
    public Planification ModifierPlanification(Planification planification) {
        return planificationRepository.save(planification);
    }

    @Override
    public List<Planification> AfficherPlanification() {
        return planificationRepository.findAll();
    }

    @Override
    public void SupprimerPlanification(Long id) {
        planificationRepository.deleteById(id);
    }

    @Override
    public Optional<Planification> getPlanificationById(Long id) {
        return planificationRepository.findById(id);
    }
}

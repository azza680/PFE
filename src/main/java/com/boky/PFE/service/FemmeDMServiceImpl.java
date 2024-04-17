package com.boky.PFE.service;

import com.boky.PFE.entite.FemmeDeMenage;
import com.boky.PFE.repository.FemmeDMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class FemmeDMServiceImpl implements FemmeDMService
{
    @Autowired
    FemmeDMRepository femmeDMRepository;
    @Override
    public FemmeDeMenage AjouterFemmeDM(FemmeDeMenage femmeDeMenage) {
        return femmeDMRepository.save(femmeDeMenage);
    }

    @Override
    public FemmeDeMenage ModifierFemmeDM(FemmeDeMenage femmeDeMenage) {
        return femmeDMRepository.save(femmeDeMenage);
    }

    @Override
    public List<FemmeDeMenage> AfficherFemmeDM() {
        return femmeDMRepository.findAll();
    }

    @Override
    public void SupprimerFemmeDM(Long id) {
        femmeDMRepository.deleteById(id);
    }

    @Override
    public Optional<FemmeDeMenage> getFemmeDMById(Long id) {
        return femmeDMRepository.findById(id);
    }
}

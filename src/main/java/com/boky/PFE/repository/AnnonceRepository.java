package com.boky.PFE.repository;

import com.boky.PFE.entite.Admin;
import com.boky.PFE.entite.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnonceRepository extends JpaRepository<Annonce,Long> {
}

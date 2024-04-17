package com.boky.PFE.repository;

import com.boky.PFE.entite.Admin;
import com.boky.PFE.entite.Annonceur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnonceurRepository extends JpaRepository<Annonceur,Long>
{

    boolean existsByEmail(String email);

    Annonceur findAnnonceurByEmail(String email);
}

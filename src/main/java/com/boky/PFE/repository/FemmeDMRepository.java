package com.boky.PFE.repository;

import com.boky.PFE.entite.FemmeDeMenage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FemmeDMRepository extends JpaRepository<FemmeDeMenage,Long> {
    boolean existsByEmail(String email);

    FemmeDeMenage findFemmeDMByEmail(String email);
}

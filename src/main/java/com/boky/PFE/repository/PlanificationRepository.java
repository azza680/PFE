package com.boky.PFE.repository;

import com.boky.PFE.entite.Planification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanificationRepository extends JpaRepository<Planification,Long> {
}

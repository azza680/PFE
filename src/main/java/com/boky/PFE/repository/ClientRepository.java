package com.boky.PFE.repository;

import com.boky.PFE.entite.Admin;
import com.boky.PFE.entite.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long>
{
    boolean existsByEmail(String email);

    Client findClientByEmail(String email);
}

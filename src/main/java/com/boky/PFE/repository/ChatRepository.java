package com.boky.PFE.repository;

import com.boky.PFE.entite.Admin;
import com.boky.PFE.entite.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat,Long> {
}

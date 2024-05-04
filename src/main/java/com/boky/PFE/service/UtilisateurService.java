package com.boky.PFE.service;

import com.boky.PFE.entite.Annonceur;
import com.boky.PFE.entite.Utilisateur;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UtilisateurService {

    ResponseEntity<Object> AjouterUtilisateur(Utilisateur utilisateur);
    Utilisateur ModifierUtilisateur(Utilisateur utilisateur, long id);
    List<Utilisateur> AfficherUtilisateur();
    void SupprimerUtilisateur (Long id);
    Optional<Utilisateur> getUtilisateurById(Long id);

    void modifierPhotoDeProfil(Long userId, MultipartFile photo)throws IOException, UtilisateurNotFoundException;
    ResponseEntity<?> ConfirmationEmail (String confirmationEmail);

}

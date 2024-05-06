package com.boky.PFE.service;

import com.boky.PFE.Beans.ReservationRQ;
import com.boky.PFE.entite.Annonce;
import com.boky.PFE.entite.Reservation;
import com.boky.PFE.entite.Utilisateur;
import com.boky.PFE.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements  ReservationService
{
    @Autowired
    AnnonceService annonceService;
    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    ReservationRepository reservationRepository;
    @Override
    public Reservation AjouterReservation(ReservationRQ reservationRQ){
        Optional<Annonce> annonce = annonceService.getAnnonceById(reservationRQ.getId_annonce());
        Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateurById(reservationRQ.getId_client());
        if (annonce.isPresent() && utilisateur.isPresent()) {
            Reservation reservation = new Reservation();
            reservation.setAnnonce(annonce.get());
            reservation.setUtilisateur(utilisateur.get());
            return reservationRepository.save(reservation);}
        else{
            return null;}
    }

    @Override
    public List<Reservation> AfficherReservation() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> listeReservationByUtilisateur(Long id) {
        return reservationRepository.findByutilisateurId(id);
    }

}

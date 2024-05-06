package com.boky.PFE.service;

import com.boky.PFE.Beans.ReservationRQ;
import com.boky.PFE.entite.Reservation;
import java.util.List;

public interface ReservationService
{
    Reservation AjouterReservation(ReservationRQ reservationRQ);
    List<Reservation> AfficherReservation();
    List<Reservation> listeReservationByUtilisateur(Long id );
}

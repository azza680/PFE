package com.boky.PFE.restController;

import com.boky.PFE.Beans.ReservationRQ;
import com.boky.PFE.entite.Reservation;
import com.boky.PFE.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/Reservation")
public class ReservationRestController
{
    @Autowired
    ReservationService reservationService;
    @RequestMapping(method = RequestMethod.POST)
    public Reservation ajouterReservation(@RequestBody ReservationRQ reservationRQ){
        System.out.println("reserverRq"+reservationRQ);
        return reservationService.AjouterReservation(reservationRQ);
    }
    @RequestMapping("get-all-by-id-utilisateur/{id}")
    public List<Reservation> listReservationByUtilisateur(@PathVariable Long id){
        return reservationService.listeReservationByUtilisateur(id);
    }
}

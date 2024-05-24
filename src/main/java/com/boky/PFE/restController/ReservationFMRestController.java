package com.boky.PFE.restController;

import com.boky.PFE.Beans.SavereservationFM;
import com.boky.PFE.entite.Planification;
import com.boky.PFE.entite.ReservationFM;
import com.boky.PFE.entite.Utilisateur;
import com.boky.PFE.service.ReservationFMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/ReservationFM")
public class ReservationFMRestController {

    @Autowired
    private ReservationFMService reservationFMService;

    @PostMapping
    public ReservationFM ajouterReservationFM(@RequestBody SavereservationFM model) {
        System.out.println("reserverFm " + model);
        return reservationFMService.AjouterReservationFM(model);
    }

    @GetMapping
    public List<ReservationFM> AfficherReservationFM() {
        return reservationFMService.AfficherReservationFM();
    }

    @GetMapping("/{id}")
    public Optional<ReservationFM> getReservationFMById(@PathVariable("id") long id) {
        return reservationFMService.getReservationFMById(id);
    }

    @PutMapping("/{id}")
    public ReservationFM ModifierReservationFM(@PathVariable("id") Long id, @RequestBody ReservationFM reservationFM) {
        reservationFM.setId(id);
        return reservationFMService.ModifierReservationFM(reservationFM);
    }

    @GetMapping("/get-all-by-id-utilisateur/{id}")
    public List<ReservationFM> listReservationFMByUtilisateur(@PathVariable Long id) {
        return reservationFMService.listeReservationFMByUtilisateur(id);
    }

    @GetMapping("/get-client/{id}")
    public Utilisateur ClientByReservationFM(@PathVariable Long id) {
        return reservationFMService.ClientByReservationFM(id);
    }

    @GetMapping("/get-planification/{id}")
    public Planification planificationByReservation(@PathVariable Long id) {
        return reservationFMService.planificationByReservationFM(id);
    }
}

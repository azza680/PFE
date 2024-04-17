package com.boky.PFE.restController;


import com.boky.PFE.entite.Admin;
import com.boky.PFE.entite.Planification;
import com.boky.PFE.repository.PlanificationRepository;
import com.boky.PFE.service.PlanificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/Planification")
public class PlanificationRestController
{

    @Autowired
    PlanificationRepository planificationRepository;
    @RequestMapping(method = RequestMethod.POST )
    public ResponseEntity<Planification> AjouterPlanification (@RequestBody Planification planification)
    {
            Planification savedUser = planificationRepository.save(planification);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);

    }
    @Autowired
    PlanificationService planificationService;
    @RequestMapping(method = RequestMethod.GET)
    public List<Planification> AfficherPlanification()
    {
        return planificationService.AfficherPlanification();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE )

    public void SupprimerPlanification(@PathVariable("id") Long id){
        planificationService.SupprimerPlanification(id);

    }
    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public Planification ModifierPlanification(@PathVariable("id")Long id, @RequestBody Planification planification)
    {
        Planification newPlanification = planificationService.ModifierPlanification(planification);
        return newPlanification;
    }
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Optional<Planification> getPlanificationById(@PathVariable("id") long id){

        Optional<Planification> planification= planificationService.getPlanificationById(id);
        return planification;
    }
}

package com.boky.PFE.entite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Optional;

@Entity

public class Reservation
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private Date date_arrivee;
    private Date date_depart;
    private long nb_nuit;
    private long nb_vacancier;

    @ManyToOne
    Utilisateur utilisateur;
    @ManyToOne
    Annonce annonce ;

    public long getNb_vacancier() {
        return nb_vacancier;
    }

    public void setNb_vacancier(long nb_vacancier) {
        this.nb_vacancier = nb_vacancier;
    }

    public Date getDate_arrivee() {
        return date_arrivee;
    }

    public void setDate_arrivee(Date date_arrivee) {
        this.date_arrivee = date_arrivee;
    }

    public Date getDate_depart() {
        return date_depart;
    }

    public void setDate_depart(Date date_depart) {
        this.date_depart = date_depart;
    }

    public long getNb_nuit() {
        return nb_nuit;
    }

    public void setNb_nuit(long nb_nuit) {
        this.nb_nuit = nb_nuit;
    }

    public Reservation() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }
    public Reservation( Utilisateur utilisateur, Annonce annonce)
    {
        this.utilisateur=utilisateur;
        this.annonce=annonce;
    }

    public Reservation(Long id, Date date_arrivee, Date date_depart, Utilisateur utilisateur, Annonce annonce) {
        this.id = id;
        this.date_arrivee = date_arrivee;
        this.date_depart = date_depart;
        this.nb_nuit = this.date_depart.getTime()-this.date_arrivee.getTime();
        this.utilisateur = utilisateur;
        this.annonce = annonce;
    }
}

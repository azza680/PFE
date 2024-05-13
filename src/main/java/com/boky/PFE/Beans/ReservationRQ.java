package com.boky.PFE.Beans;

import java.util.Date;

public class ReservationRQ
{
    private long id_client;
    private long id_annonce;
    private Date date_arrivee;
    private Date date_depart;
    private long nb_nuit;
    private long nb_vacancier;

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

    public long getId_client() {
        return id_client;
    }

    public void setId_client(long id_client) {
        this.id_client = id_client;
    }

    public long getId_annonce() {
        return id_annonce;
    }

    public void setId_annonce(long id_annonce) {
        this.id_annonce = id_annonce;
    }
}

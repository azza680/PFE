package com.boky.PFE.Beans;

public class ReservationRQ
{
    private long id_client;
    private long id_annonce;

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

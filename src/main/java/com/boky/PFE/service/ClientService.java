package com.boky.PFE.service;

import com.boky.PFE.entite.Admin;
import com.boky.PFE.entite.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService
{
    Client AjouterClient(Client client);
    Client ModifierClient(Client client);
    List<Client> AfficherClient();
    void SupprimerClient (Long id);
    Optional<Client> getClientById(Long id);
}

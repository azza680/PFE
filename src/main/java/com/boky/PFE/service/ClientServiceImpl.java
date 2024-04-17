package com.boky.PFE.service;

import com.boky.PFE.entite.Admin;
import com.boky.PFE.entite.Client;
import com.boky.PFE.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService
{
    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client AjouterClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client ModifierClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> AfficherClient() {
        return clientRepository.findAll();
    }
    public void SupprimerClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }
}

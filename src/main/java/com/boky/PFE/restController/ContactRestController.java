package com.boky.PFE.restController;

import com.boky.PFE.entite.Contact;
import com.boky.PFE.repository.ContactRepository;
import com.boky.PFE.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/Contact")
public class ContactRestController
{
    @Autowired
    ContactRepository contactRepository;
    @RequestMapping(method = RequestMethod.POST )
    public ResponseEntity<Contact> AjouterContact (@RequestBody Contact contact)
    {
            Contact savedUser = contactRepository.save(contact);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);

    }
    @Autowired
    ContactService contactService;
    @RequestMapping(method = RequestMethod.GET)
    public List<Contact> AfficherContact()
    {
        return contactService.AfficherContact();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE )

    public void SupprimerContact(@PathVariable("id") Long id){
        contactService.SupprimerContact(id);

    }
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Optional<Contact> getContactById(@PathVariable("id") long id){

        Optional<Contact> contact = contactService.getContactById(id);
        return contact;
    }
}

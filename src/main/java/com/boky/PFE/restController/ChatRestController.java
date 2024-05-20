package com.boky.PFE.restController;

import com.boky.PFE.entite.Chat;
import com.boky.PFE.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/Chat")
public class ChatRestController
{
    @Autowired
    ChatService chatService;
    @RequestMapping(method = RequestMethod.POST)
    public Chat ajouterCategorie (@RequestBody Chat chat){
        return chatService.ajouterChat(chat);}

    @RequestMapping(method = RequestMethod.PUT)
    public Chat modifierChat (@RequestBody Chat chat){
        return chatService.modifierChat(chat);}

    @RequestMapping(value ="/{id}",method = RequestMethod.DELETE)
    public void supprimerChat(@PathVariable("id") Long id)
    {
        chatService.suppeimerChatbyId(id);
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<Chat> afficherChat(){
        return chatService.listChats();}
}

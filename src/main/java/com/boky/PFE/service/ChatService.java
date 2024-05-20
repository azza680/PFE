package com.boky.PFE.service;

import com.boky.PFE.entite.Chat;

import java.util.List;

public interface ChatService {
    Chat ajouterChat (Chat chat);
    Chat modifierChat (Chat chat);
    void supprimerChat (Chat chat);
    void suppeimerChatbyId (Long idChat);
    List<Chat> listChats();
}

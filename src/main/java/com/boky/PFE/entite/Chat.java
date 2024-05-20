package com.boky.PFE.entite;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
@Entity

public class Chat
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idChat;
    private String messages ;
    private String Sender;
    private Date date;


    public Chat() {
        super();
    }

    public Chat(String messages, String sender, Date date, String role) {
        this.messages = messages;
        Sender = sender;
        this.date = date;

    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}

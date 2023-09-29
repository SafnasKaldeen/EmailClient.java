package com.cyberflash.emailclient;

import java.io.Serializable;
import java.time.LocalDate;

//creating an Class named E_Mail which can be implemented by serializable interface
public class E_Mail implements Serializable {
    private LocalDate sent_Date;
    private String Target_Email;
    private String Subject;
    private String Message;


    //constructor for the E_Mail class
    public E_Mail(String target_Email, String subject, String message) {
        this.sent_Date = LocalDate.now();
        this.Target_Email = target_Email;
        this.Subject = subject;
        this.Message = message;
    }

    //getter methods for the E_Mail class
    public String getTarget_Email() {
        return Target_Email;
    }

    public String getSubject() {
        return Subject;
    }

    public String getMessage() {
        return Message;
    }

    public LocalDate getSent_Date() {
        return sent_Date;
    }
}

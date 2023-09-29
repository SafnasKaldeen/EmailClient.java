package com.cyberflash.emailclient;

import java.time.LocalDate;

//creating an Class named Personal_Recepients which is inherited Recepients abstract class
public class Personal_Recepients extends Recepients {
    private LocalDate birthday;
    private String nick_name;

    //constructor for the Personal_Recepients class
    public Personal_Recepients(String name , String nick_name, String email , LocalDate birthday) {
        super(name, email);
        this.birthday = birthday;
        this.nick_name = nick_name;
        Recepients_Count++;
    }
}
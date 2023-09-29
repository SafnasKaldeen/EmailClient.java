package com.cyberflash.emailclient;

import java.time.LocalDate;

//creating an Class named Official_Friends which is inherited Recepients abstract class
public class Official_Friends extends Official_Recepients {
    private LocalDate birthday;

    //constructor for the Official_Friends class
    public Official_Friends(String name, String email, String designation, LocalDate birthday) {
        super(name, email, designation);
        this.birthday = birthday;
        Recepients_Count++;
    }
}

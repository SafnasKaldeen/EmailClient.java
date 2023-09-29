package com.cyberflash.emailclient;

//creating an Class named Official_Recepients which is inherited Recepients abstract class
public class Official_Recepients extends Recepients {
    private static String designation;

    //constructor for the Official_Recepients class
    public Official_Recepients(String name, String email , String designation) {
        super(name, email);
        this.designation = designation;
        Recepients_Count++;
    }
}


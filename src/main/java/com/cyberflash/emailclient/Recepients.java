package com.cyberflash.emailclient;

//creating an abstract class which is extended by the other 3 classes
public abstract class Recepients {
    protected String name;
    protected String email;
    public static int Recepients_Count = 0;

    //constructor for the abstract class
    public Recepients(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

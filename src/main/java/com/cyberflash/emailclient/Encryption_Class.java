package com.cyberflash.emailclient;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Vector;

//creating an Class named Encryption_Class to serialize and deserialize the E_Mail class
public class Encryption_Class {
    //creating a vector to store Deserialized E_Mail objects
    private static Vector<E_Mail> Decrypted_Emails_Vector = new Vector<>();

    //method to serialize the E_Mail objects
    public static void Serialize_Email(E_Mail e_mail) {
        boolean exists = Files.exists(Path.of("Encrypted_Mails.ser"));
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("Encrypted_Mails.ser", true);
            ObjectOutputStream objectOutputStream;

            if (exists) {
                objectOutputStream = new ObjectOutputStream(fileOutputStream) {
                    @Override
                    protected void writeStreamHeader() {
                    }
                };

            } else {
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
            }

            objectOutputStream.writeObject(e_mail);
            objectOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This reads the .ser file and returns a vector with all emails deserialized.
    public static Vector<E_Mail> Deserialize_Emails(){
        try {
            FileInputStream fileInputStream = new FileInputStream("Encrypted_Mails.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            E_Mail Decrypted_Mail = (E_Mail) objectInputStream.readObject();

            while (Decrypted_Mail != null) {
                Decrypted_Emails_Vector.add(Decrypted_Mail);
                try {
                    Decrypted_Mail = (E_Mail) objectInputStream.readObject();
                } catch (EOFException e) {
                    break;
                }
            }
            fileInputStream.close();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Decrypted_Emails_Vector;
    }
}





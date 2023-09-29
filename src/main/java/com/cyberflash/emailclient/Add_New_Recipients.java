package com.cyberflash.emailclient;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

//Creting a class named Add_New_Recepients which is holding the methods for Analyzing and writing new Recepient details
// to the existing clientList.txt.
public class Add_New_Recipients {
    //Creating a String Vector to hold the new Recepients details from the clientList.txt.
    private static Vector<String> Recepient_Vector = new Vector<>();

    //Static Variable to hold the count of the Recepients even after program restarted.
    private static int Recepients_Count = 0;

    //Creating a static method to analyze the Recepient Details from the main class and write them to the clientList.txt
    public static void Analyze_Recipients(String[] Recepient_Character_Array_Main, String[] Recepient_Character_Array_Sub) {

        //Switch statement to analyze the type of the Recepient and write the details to the clientList.txt.
        switch (Recepient_Character_Array_Main[0].toUpperCase()) {
            //If the type of the Recepient is PERSONAL then write the relevant details to the clientList.txt and
            // increment the recepient count by one because another string is just added to the clientList.txt
            case "PERSONAL":

                String Data1 = Recepient_Character_Array_Sub[0].strip();
                String Data2 = Recepient_Character_Array_Sub[1].strip();
                String Data3 = Recepient_Character_Array_Sub[2].strip();
                LocalDate Data4 = LocalDate.parse(Recepient_Character_Array_Sub[3].strip() , DateTimeFormatter.ofPattern("yyyy/MM/dd"));

                Personal_Recepients personal_recepients = new Personal_Recepients(Data1 , Data2 , Data3 , Data4);
                Write_Into_File(Recepient_Character_Array_Main[0].strip() + " -> "
                        + Recepient_Character_Array_Sub[0].strip() + " -> "
                        + Recepient_Character_Array_Sub[1].strip() + " -> "
                        + Recepient_Character_Array_Sub[2].strip() + " -> "
                        + LocalDate.parse(Recepient_Character_Array_Sub[3].strip() , DateTimeFormatter.ofPattern("yyyy/MM/dd")));
                Recepients_Count++;

                break;

            //If the type of the Recepient is OFFICIAL then write the relevant details to the clientList.txt and
            // increment the recepient count by one because another string is just added to the clientList.txt
            case ("OFFICIAL"):

                String Data5 = Recepient_Character_Array_Sub[0].strip();
                String Data6 = Recepient_Character_Array_Sub[1].strip();
                String Data7 = Recepient_Character_Array_Sub[2].strip();

                Official_Recepients official_Recepients = new Official_Recepients(Data5 , Data6 , Data7);
                Write_Into_File(Recepient_Character_Array_Main[0].strip() + " -> "
                        + Recepient_Character_Array_Sub[0].strip() + " -> "
                        + Recepient_Character_Array_Sub[1].strip() + " -> "
                        + Recepient_Character_Array_Sub[2].strip());
                Recepients_Count++;

                break;

            //If the type of the Recepient is OFFICE_FRIEND then write the relevant details to the clientList.txt and
            // increment the recepient count by one because another string is just added to the clientList.txt
            case ("OFFICE_FRIEND"):

                String Data8 = Recepient_Character_Array_Sub[0].strip();
                String Data9 = Recepient_Character_Array_Sub[1].strip();
                String Data10 = Recepient_Character_Array_Sub[2].strip();
                LocalDate Data11 = LocalDate.parse(Recepient_Character_Array_Sub[3].strip() , DateTimeFormatter.ofPattern("yyyy/MM/dd"));

                Official_Friends official_friends = new Official_Friends(Data8 , Data9 , Data10 , Data11);
                Write_Into_File(Recepient_Character_Array_Main[0].strip() + " -> "
                        + Recepient_Character_Array_Sub[0].strip() + " -> "
                        + Recepient_Character_Array_Sub[1].strip() + " -> "
                        + Recepient_Character_Array_Sub[2].strip() + " -> "
                        + LocalDate.parse(Recepient_Character_Array_Sub[3].strip() , DateTimeFormatter.ofPattern("yyyy/MM/dd")));
                Recepients_Count++;

                break;

            //Exception handling for the invalid type of the Recepient.
            default:
                System.out.println("Invalid Recepient Type");

                break;
        }
    }

    //Creating a static method to write the new Recepient details to the clientList.txt by using buffer writer.
    public static void Write_Into_File(String Details) {
        try {
            //creates a new file instance
            File Recepient_Record = new File("clientList.txt");

            FileWriter myWriter = new FileWriter("clientList.txt", true);

            BufferedWriter bufferWriter = new BufferedWriter(myWriter);
            //write the new Recepient details to the clientList.txt line by line
            bufferWriter.write(Details + "\n");
            bufferWriter.close();
            myWriter.close();

            System.out.println("\n" + "-----------Recepient Details are successfully wrote to the file----------- \n");

        //Exception Handling
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //setting the Recepients_Vector by the Recepients in the clientList.txt.
    public static void setRecepient_Vector(Vector<String> Recepient_Vector) {
        try {
            //creates a new file instance
            File Recepient_Record = new File("clientList.txt");

            //reads the file
            FileReader fileReader = new FileReader(Recepient_Record);
            //creates a buffering character input stream
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                Recepient_Vector.add(line);
            }
            //closes the stream and release the resources
            fileReader.close();
        //Exception Handling
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //getter method for the Recepients_Vector (Encapsulation)
    public static Vector<String> getRecepient_Vector() {
        return Recepient_Vector;
    }

    //setter method for the Recepients_Count (Encapsulation)
    public static void setRecepients_Count(int recepients_Count) {
        Recepients_Count = recepients_Count;
    }

    //getter method for the Recepients_Count (Encapsulation)
    public static int getRecepients_Count() {
        return Recepients_Count;
    }
}




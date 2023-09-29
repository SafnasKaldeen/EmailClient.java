package com.cyberflash.emailclient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import static java.lang.System.exit;

//Main class - Starting Point Class whould be named as the file name and must contain main function.
public class Email_Client {
  public static void main(String[] args) {

      //Filling an empty Vector by Recepient objects which are saved in clientList.txt.
      Add_New_Recipients.setRecepient_Vector(Add_New_Recipients.getRecepient_Vector());

      //Setting the recepient count to the count of the recepient objects in the Vector , because the count should not
      // simply return zero when the case 5 is called.
      Add_New_Recipients.setRecepients_Count(Add_New_Recipients.getRecepient_Vector().size());

      //Iterating through the RecepientVector and split them using " -> " as a delimiter and put the results in
      // an array called Today_Born_Details_Array.

      //Iterating through the SerializedEmails from "Encrypted_Mails.ser" and Deserialize the Emails
      // and add to Deserialized_Mails_Vector.
      Vector<E_Mail> DeSerialized_Mails_Vector = Encryption_Class.Deserialize_Emails();

      for (int i = 0; i < Add_New_Recipients.getRecepient_Vector().size(); i++) {
          String[] Today_Born_Details_Array = Add_New_Recipients.getRecepient_Vector().get(i).split(" -> ");
          //Make sure Recepients without birthdays objects are not go through the if condition.
          if (Today_Born_Details_Array.length == 5) {
              boolean Condition1 = LocalDate.parse(Today_Born_Details_Array[4].strip()).getMonthValue() == LocalDate.now().getMonthValue();
              boolean Condition2 = LocalDate.parse(Today_Born_Details_Array[4].strip()).getDayOfMonth() == LocalDate.now().getDayOfMonth();

              //Evaluate weather the current recepient's birthday is today or not ?.
              if (Condition1 && Condition2) {
                  if (Today_Born_Details_Array[0].strip().equalsIgnoreCase("PERSONAL")) {
                      String Mail_ID = Today_Born_Details_Array[2].strip();
                      String Subject = "Happy Birthday Friend";
                      String Message = "hugs and love on your birthday. \n\nSafnas";

                      //send the wish email to the current personal recepient if today is their birthday.
                      SendEmailTLS.SendCustomMail(new E_Mail(Mail_ID, Subject, Message));

                  } else if (Today_Born_Details_Array[0].strip().equalsIgnoreCase("OFFICE_FRIEND")) {
                      String Mail_ID = Today_Born_Details_Array[2].strip();
                      String Subject = "Happy BirthDay Mate";
                      String Message = "Wish you a Happy Birthday. \n\nSafnas";

                      //send the wish email to the current Official_Friend recepient if today is their birthday.
                      SendEmailTLS.SendCustomMail(new E_Mail(Mail_ID, Subject, Message));
                  }
              }
          }
      }

    //Looping through the cases untill the user want to exit the program.
    while (true) {

        //Creating a scanner class to read the user input.
        Scanner scanner = new Scanner(System.in);

        //Printing the menu to the user.
        System.out.println("Enter option type: " + "\n"
                + "1 - Adding a new recipient\n"
                + "2 - Sending an email\n"
                + "3 - Printing out all the recipients who have birthdays\n"
                + "4 - Printing out details of all the emails sent\n"
                + "5 - Printing out the number of recipient objects in the application \n");

        //Seperating the Terminal Output
        System.out.println("===============================================================================================================");

        //Reading the user input choice.
        System.out.print("Choice: ");
        String option = scanner.nextLine();

        try {
            //Switch cases to perform different operations.
            switch (Integer.parseInt(option)) {

                //Case 1 - Adding a new recipient.
                case 1:
                    System.out.println("\nType your input in the following format: \n"
                            + "Personal: <name>,<email>,<nick-name>,<birthday> \n"
                            + "Official: <name>,<email>,<designation> \n"
                            + "Office_friend: <name>,<email>,<designation>,<birthday> \n");

                    String input = scanner.nextLine();
                    String[] Recepient_Character_Array_Main = input.split(": ");
                    String[] Recepient_Character_Array_Sub = Recepient_Character_Array_Main[1].split(",");

                    //Checking the type of the recepient and calling the appropriate method to add the recepient.
                    Add_New_Recipients.Analyze_Recipients(Recepient_Character_Array_Main, Recepient_Character_Array_Sub);

                    break;

                //case 2 - Sending a custom email by extracting essential parameters from the terminal input.
                case 2:
                    System.out.print("\nType your input in the following format -> <email>,<subject>,<content>: ");
                    String Email_Parameters = scanner.nextLine();
                    String[] Email_Parameters_Array = Email_Parameters.split(",");

                    String Mail_ID = Email_Parameters_Array[0].strip();
                    String Subject = Email_Parameters_Array[1].strip();
                    String Message = Email_Parameters_Array[2].strip();

                    //Sending the custom email using SendEmailTLS class.
                    SendEmailTLS.SendCustomMail(new E_Mail(Mail_ID, Subject, Message));

                    break;

                // case 3 - Printing out all the recipients who have birthdays on the given date.
                case 3:
                    System.out.print("\nType the Date you want to analyze in <yyyy/MM/dd> format: ");
                    String DateString = scanner.next();

                    //Iterating through the RecepientVector.
                    for (int i = 0; i < Add_New_Recipients.getRecepient_Vector().size(); i++) {
                        String[] Current_Recepient_Details_Array = Add_New_Recipients.getRecepient_Vector().get(i).split(" -> ");

                        //converting the date string to LocalDate format.
                        LocalDate Formated_DateString1 = LocalDate.parse(DateString.strip(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));

                        //Make sure Recepients without birthdays objects are not go through the if condition.
                        if (Current_Recepient_Details_Array.length == 5) {
                            //checking weather the current recepient's birthday is on the given date or not , if it's then print the name.
                            if (LocalDate.parse(Current_Recepient_Details_Array[4].strip()).getMonth().equals(Formated_DateString1.getMonth()) &&
                                LocalDate.parse(Current_Recepient_Details_Array[4].strip()).getDayOfMonth() == Formated_DateString1.getDayOfMonth()) {
                                System.out.println(Current_Recepient_Details_Array[1]);
                            }
                        }
                    }

                    break;

                //case 4 - Printing out the details of all the emails sent on the given date.
                case 4:
                    System.out.print("\nType the Date you want to analyze in <yyyy/MM/dd> format: ");
                    String DateString2 = scanner.next();

                    LocalDate Formated_DateString2 = LocalDate.parse(DateString2.strip(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                    //Iterating through the Deserialized_Mails_Vector checking weather the current Email sent date is
                    // the given date or not , if it's then print the subject and recepient of the mail.
                    for (int i = 0; i < DeSerialized_Mails_Vector.size(); i++) {
                        if (DeSerialized_Mails_Vector.get(i).getSent_Date().equals(Formated_DateString2)) {
                            System.out.println(i + 1 +  "-> " + "Detail of the EMails sent today -> "
                                    + "Recepient: " + DeSerialized_Mails_Vector.get(i).getTarget_Email() + " , "
                                    + "Subject: " + DeSerialized_Mails_Vector.get(i).getSubject());
                        }
                    }

                    break;

                //case 5 - Printing out the number of recipient objects in the application clientList.txt file using static variable.
                case 5:
                    System.out.println("Number of recipients: " + Add_New_Recipients.getRecepients_Count() + "\n");

                    break;

            }

            //Asking the user if he want to continue or not after completing a case.
            System.out.print("\nDo you want to peform another Action (Y/N): ");

            String Option3 = scanner.next();

            //If the user want to continue then continue the loop else exit the program.
            if (Option3.equalsIgnoreCase("Y")) {
                continue;
            } else if (Option3.equalsIgnoreCase("N")) {
                exit(0);
            } else {
                System.out.println("\nInvalid Input");
                exit(0);
            }
        } catch (Exception e) {
            System.out.println("\nInvalid Input\n");
            continue;
        }
    }
  }
}
















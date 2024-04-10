package com.magicvet;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static String PASSWORD = "default";
    static Scanner SCANNER = new Scanner(System.in);
    static String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    static String FIRSTNAME="^[a-zA-Z]{3,}$";
    static String LASTNAME="^[a-zA-Z-]{3,}$";

    public static void main(String[] args) {
        run();
    }

    static void run() {
        if (auth()) {
            registerNewClient();
        }
    }

    private static boolean auth() {
        boolean accepted = false;
        for (int i = 0; i < 3; i++) {
            System.out.print("Password: ");
            String input = SCANNER.nextLine();

            if (PASSWORD.equals(input)) {
                accepted = true;
                break;
            } else {
                System.out.println("Access denied. Please check your password.");
            }
        }
        System.out.println(accepted ? "Welcome to the Magic Vet!" : "Application has been blocked");
        return accepted;
    }

    private static void registerNewClient() {
        System.out.println("Please provide client details");
        System.out.print("Email: ");
        String email = SCANNER.nextLine();
        System.out.print("First name: ");
        String firstName=SCANNER.nextLine();
        System.out.print("Last name: ");
        String lastName = SCANNER.nextLine();
        if (isEmailValid(email)&&isFirstNameValid(firstName)&&isLastNameValid(lastName)) {
            Client client = buildClient(email,firstName,lastName);
            System.out.println("New Client: " + client.firstName + " " + client.lastName + " (" + client.email + ")");
        } else {
            System.out.println("Provided data is invalid. Please check");
        }
    }

    static Client buildClient(String email, String firstName, String lastName) {
        Client client = new Client();
        client.email = email;
        client.firstName =firstName;
        client.lastName=lastName;
        return client;
    }

    static boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        boolean isEmail=matcher.matches();
        if (!isEmail){
            System.out.println("Provided email is invalid");
        }
        return isEmail;
    }
    static boolean isFirstNameValid(String firstName) {
        Pattern pattern = Pattern.compile(FIRSTNAME);
        Matcher matcher = pattern.matcher(firstName);
        boolean isFirstName=matcher.matches();
        if (!isFirstName){
            System.out.println("Provided first name is invalid");
        }
        return isFirstName;
    }
    static boolean isLastNameValid(String lastName) {
        Pattern pattern = Pattern.compile(LASTNAME);
        Matcher matcher = pattern.matcher(lastName);
        boolean isLastName=matcher.matches();
        if (!isLastName){
            System.out.println("Provided last name is invalid");
        }
        return isLastName;
    }

}
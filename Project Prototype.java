//Prototype Project by [Zandile Dladla]
// Student Number: [4120815]
// Project Name: Babysitting Management System

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Sunshine Sitters");


        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter their registration information
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        scanner.close();

        // Print the captured user information
        System.out.println("\nThank you for registering!");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Email Address: " + email);

        //The purpose of the project
        System.out.println("\nThis prototype demonstrates a user registration process.");
        System.out.println("It captures the user's first name, last name, email address and password to allow parents to login to the application");




  }
}
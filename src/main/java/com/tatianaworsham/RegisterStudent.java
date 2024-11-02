package com.tatianaworsham;

import java.util.Scanner;

import com.tatianaworsham.models.Student;

public class RegisterStudent {

    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private int studentId;
    // The registration requires the student to enter their first name, last name, addresss, and phone number
    // it will seve the information to the file " studentRegistration.txt".
    // It will create student ID (random number of 3 digits and return that student Id to the user.

    /**
     * This constructor initializes the student's first name, last name,
     * address, and phone number.
     *
     * @param firstName
     * @param lastName
     * @param address
     * @param phoneNumber
     * @param studentId
     */
    public RegisterStudent() {

        // Create a Scanner object for keyboard input.
        try (Scanner keyboard = new Scanner(System.in)) {
            // Get the student's first name.
            System.out.print("Enter your first name: ");
            String fname = keyboard.nextLine();
            // Get the student's last name.
            System.out.print("Enter your last name: ");
            String lname = keyboard.nextLine();
            // Get the student's address.
            System.out.print("Enter your address: ");
            String address = keyboard.nextLine();
            // Get the student's phone number.
            System.out.print("Enter your phone number: ");
            String phoneNumber = keyboard.nextLine();
            // Create a student object.
            int studentId = (int)(Math.random() * 900) + 100;
            Student student = new Student(fname, lname, address, phoneNumber, studentId);
            // Display the student's registration information.
            System.out.println(student);
        }
    }

    /**
     * The set method sets a value for eash field.
     *
     * @param fname The student's first name.
     * @param lname The student's last name.
     * @param address The student's address.
     * @param phoneNumber The student's phone number.
     */
    public void set(String fname, String lname, String address, String phoneNumber) {
        firstName = fname;
        lastName = lname;
        address = address;
        phoneNumber = phoneNumber;
    }

    /**
     * toString method
     *
     * @return A string containing the student's registration information.
     */
    public String toString() {
        // Create a string representing the object.
        String str = "First Name: " + firstName
                + "\n" + "Last Name: " + lastName
                + "\n" + "Address: " + address
                + "\n" + "Phone Number: " + phoneNumber;
        // Return the string.
        return str;
    }
}

package com.tatianaworsham.models;

import java.util.ArrayList;

public class Student {
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private int studentId;
    private ArrayList<Subject> subjects;

    public Student(String firstName, String lastName, String address, String phoneNumber, int studentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.studentId = studentId;
        this.subjects = new ArrayList<Subject>();
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStudentId() {
        return this.studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    // Get and set the student's name.
    public String getStudentName() {
        return this.firstName + " " + this.lastName;
    }
    public void setStudentName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public ArrayList<Subject> getSubjects() {
        return this.subjects;
    }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }

    @Override
    public String toString() {
        // Create a string representing the object.
        String str = "First Name: " + firstName
                + "\n" + "Last Name: " + lastName
                + "\n" + "Address: " + address
                + "\n" + "Phone Number: " + phoneNumber
                + "\n" + "Student ID: " + studentId;
        // Return the string.
        return str;
    }
    
}

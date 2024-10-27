package com.tatianaworsham;

import java.util.ArrayList;

public class Student {

    private String firstName;
    private String lastName;
    private int id;
    private ArrayList<Subject> courses;

    public Student() {

    }

    public Student(String firstName, String lastName, int id, ArrayList<Subject> courses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.courses = new ArrayList<>();
    }

    /**
     * Get and Set student name
     *
     * @return student name
     */

    public String getFirstName() {
        return this.firstName;
    }
    
    public String getLastName() {
        return this.lastName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }
    
    public void setLastName(String name) {
        this.lastName = name;
    }

    /**
     * Get and Set student id
     *
     * @return student id
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Subject> getSubjects() {
        return this.courses;
    }
}

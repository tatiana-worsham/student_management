package com.tatianaworsham.models;

import com.tatianaworsham.Validator;

public class Subject {
    private String courseName;
    private int courseNumber;
    private int grade;
    private Validator validator = new Validator();

    public Subject(String courseName, int courseNumber, int grade) {
        // validate these inputs before using them.
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.grade = grade;
    }
    /**
     * Get and Set subject name
     * @return subject name
     */

    public String getcourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        // validate this input is a string and not null
        this.courseName = courseName;
    }
    /**
     * Get and Set subject course number
     * @return
     */
    public int getCourseNumber() {
        // validate that this is a positive integer and not null
        return this.courseNumber;
    }

    public void setCourseNumber(int courseNumber) {
        // validate that this is a positive integer and not null
        this.courseNumber = courseNumber;
    }
    /**
     * Get and Set subject grade
     * @return subject grade
     */

    public int getGrade() {
        return this.grade;
    }

    public void setGrade(int grade) {
        // validate that this is a positive integer and not null
        this.grade = grade;
    }
}
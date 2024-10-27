package com.tatianaworsham;

public class Subject {
    private String courseName;
    private int courseNumber;
    private int grade;

    public Subject(String courseName, int courseNumber, int grade) {
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
        this.courseName = courseName;
    }
    /**
     * Get and Set subject course number
     * @return
     */
    public int getCourseNumber() {
        return this.courseNumber;
    }
    /**
     * Get and Set subject grade
     * @return subject grade
     */

    public int getGrade() {
        return this.grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
package com.tatianaworsham.models;

import java.util.ArrayList;

import com.tatianaworsham.Validator;

public class Subject {

    private String courseName;
    private int courseNumber;
    private int credits;
    private String department;
    private String Instructor;
    private int[] grade;
    private String StudentName;
    private int studentId;
    private Validator validator = new Validator();
    private final ArrayList<Student> students;

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }
    private double averageGrade;

    public Subject(String courseName, int courseNumber, String StudentName, int studentId) {
        // validate these inputs before using them.
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.StudentName = StudentName;
        this.studentId = studentId;
        this.students = new ArrayList<>();

    }
    public Subject(String courseName, int courseNumber, int credits, String department, String Instructor){
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.credits = credits;
        this.department = department;
        this.Instructor = Instructor;
        this.students = new ArrayList<>();
    }


    public Subject(String courseName, int courseNumber, String StudentName, int studentId, double averageGrade) {
        // validate these inputs before using them.
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.StudentName = StudentName;
        this.studentId = studentId;
        this.averageGrade = averageGrade;
        this.students = new ArrayList<Student>();

    }

    /**
     * Get and Set subject name
     *
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
     *
     * @return subject course number
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
     * Get and Set subject credits
     *
     * @return subject credits
     */
    public int getCredits() {
        return credits;
    }
    public void setCredits(int credits) {
        this.credits = credits;
    }
    /**
     * Get and Set subject department
     *
     * @return subject department
     */
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    /**
     * Get and Set subject Instructor
     *
     * @return subject Instructor
     */
    public String getInstructor() {
        return Instructor;
    }
    public void setInstructor(String Instructor) {
        this.Instructor = Instructor;
    }

    /**
     * Get and Set subject grade
     *
     * @return subject grade
     */

    public int[] getGrade() {
        return this.grade;
    }

    public void setGrade(int[] grade) {
        // validate that this is a positive integer and not null
        this.grade = grade;
    }

    public String getStudentName() {
        return StudentName;
    }

    public int getstudentId() {
        return studentId;
    }

    public double getAverageGrade() {
        return averageGrade;
    }
}

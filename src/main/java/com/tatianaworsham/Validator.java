package com.tatianaworsham;

public class Validator {
    public Validator() {
        // default constructor
    }

    public static boolean validateNumberFromOneToSeven(int number) {
        return number >= 1 && number <= 7;
    }
    
    public static boolean validateNumberFromOneToTen(int number) {
        return number >= 1 && number <= 10;
    }

    // define your validation methods
    public static boolean validateName(String name) {
        return name.matches("[a-zA-Z]+");
    }

    public static boolean validateAddress(String address) {
        return address.matches("[0-9]+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
    }

   
    public static boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{3}-?\\d{3}-?\\d{4}");
    }

    public static boolean validateInteger(String number) {
        // return number.matches("\\d+");
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean validateNumberRange(int number, int min, int max) {
        return number >= min && number <= max;
    }

    public static boolean validateStudentId(int studentId) {
        return studentId >= 100 && studentId <= 999;
    }

    public static boolean validateCourseName(String courseName) {
        return courseName.matches("[a-zA-Z]+");
    }

    public static boolean validateCourseNumber(int courseNumber) {
        return courseNumber > 0;
    }

    public static boolean validateGrade(int grade) {
        return grade >= 0 && grade <= 100;
    }

    public static boolean validateSubject(String courseName, int courseNumber, int grade) {
        return validateCourseName(courseName) && validateCourseNumber(courseNumber) && validateGrade(grade);
    }

    public static boolean validateStudent(String firstName, String lastName, String address, String phoneNumber, int studentId) {
        return validateName(firstName) && validateName(lastName) && validateAddress(address) && validatePhoneNumber(phoneNumber) && validateStudentId(studentId);
    }

    public static boolean validateStudent(String firstName, String lastName, String address, String phoneNumber, int studentId, String courseName, int courseNumber, int grade) {
        return validateStudent(firstName, lastName, address, phoneNumber, studentId) && validateSubject(courseName, courseNumber, grade);
    }

    public static boolean validateStudent(String firstName, String lastName, String address, String phoneNumber, int studentId, String courseName1, int courseNumber1, int grade1, String courseName2, int courseNumber2, int grade2) {
        return validateStudent(firstName, lastName, address, phoneNumber, studentId, courseName1, courseNumber1, grade1) && validateSubject(courseName2, courseNumber2, grade2);
    }

    public static boolean validateStudent(String firstName, String lastName, String address, String phoneNumber, int studentId, String courseName1, int courseNumber1, int grade1, String courseName2, int courseNumber2, int grade2, String courseName3, int courseNumber3, int grade3) {
        return validateStudent(firstName, lastName, address, phoneNumber, studentId, courseName1, courseNumber1, grade1, courseName2, courseNumber2, grade2) && validateSubject(courseName3, courseNumber3, grade3);
    }

    public static boolean validateStudent(String firstName, String lastName, String address, String phoneNumber, int studentId, String courseName1, int courseNumber1, int grade1, String courseName2, int courseNumber2, int grade2, String courseName3, int courseNumber3, int grade3, String courseName4, int courseNumber4, int grade4) {
        return validateStudent(firstName, lastName, address, phoneNumber, studentId, courseName1, courseNumber1, grade1, courseName2, courseNumber2, grade2, courseName3, courseNumber3, grade3) && validateSubject(courseName4, courseNumber4, grade4);
    }
    public static boolean validateisPassed(int grade, int minPassingScore) {
        return grade >= minPassingScore;
    }
}

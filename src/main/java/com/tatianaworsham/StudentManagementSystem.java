package com.tatianaworsham;

import java.io.BufferedReader;  // Import the BufferedReader class for reading text files
import java.io.FileReader;            // Import the File class for file handling
import java.io.FileWriter;   // Import this class to handle errors for file handling
import java.io.IOException;      // Import the FileReader class for reading text files
import java.io.PrintWriter;      // Import the FileWriter class for writing text files
import java.util.ArrayList;     // Import this class to handle errors for file handling
import java.util.Scanner;     // Import the PrintWriter class for writing text files

import com.tatianaworsham.models.Student;      // Import the ArrayList class for storing objects
import com.tatianaworsham.models.Subject;       // Import the Scanner class to read user input

public class StudentManagementSystem {

    private ArrayList<Student> students;
    private ArrayList<Subject> subjects;
    private final Scanner input;

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
        this.subjects = new ArrayList<>();
        this.input = new Scanner(System.in);
        generateSampleStudents();
        generateCoursesFile();
        this.greetUser();
        saveStudentsToFile();

    }

    private void generateSampleStudents() {
        this.students = StudentLoader.loadStudents("data\\student_information.txt");
    }

    private void saveStudentsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data\\student_information.txt"))) {
            // Write the header
            writer.println("First Name,Last Name,Address,Phone Number,Student ID");

            // Write the student information
            for (Student s : this.students) {
                writer.printf("%s,%s,%s,%s,%d%n", s.getFirstName(), s.getLastName(), s.getAddress(), s.getPhoneNumber(), s.getStudentId());
            }

            System.out.println("Student data saved to file successfully.");
        } catch (IOException e) {
            System.err.println("Error saving students data: " + e.getMessage());
        }
    }

    private void generateCoursesFile() {
        this.subjects = SubjectLoader.loadSubjects("data\\courses.txt");
    }

    private void saveSubjectsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data\\\\courses.txt"))) {

            // Write the student information
            for (Subject s : subjects) {
                writer.printf("%s,%s,%s,%s,%s%n", s.getcourseName(), s.getCourseNumber(), s.getCredits(), s.getDepartment(), s.getInstructor());
            }
            System.out.println("Student data saved to file successfully.");
        } catch (IOException e) {
            System.err.println("Error saving students data: " + e.getMessage());
        }
    }

    private void greetUser() {
        System.out.println("Welcome to the Student Management System!");
        while (true) {
            boolean validChoice;
            int choice = 0;
            do {
                System.out.println("Please select an option:");
                System.out.println("1. Register a Student");
                System.out.println("2. Unregister a Student");
                System.out.println("3. Update Student Information");
                System.out.println("4. Reports");
                System.out.println("5. Calculate Exam Grade");
                System.out.println("6. Calculate Pass/Fail Exam Grade");
                System.out.println("7. Exit");

                choice = Integer.parseInt(this.input.nextLine());
                validChoice = Validator.validateNumberFromOneToTen(choice);
                if (validChoice == false) {
                    System.out.println("Invalid choice. Please enter a number between 1 and 10.");
                }
                
            } while (!validChoice);

            switch (choice) {
                case 1 -> registerStudent();
                case 2 -> unregisterStudent();
                case 3 -> updateStudentInformation();
                case 4 -> Reports();
                case 5 -> calculateExamGrade();
                case 6 -> calculatePassFailExamGrade();
                case 7 -> {
                    System.out.println("Exiting the system...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void calculatePassFailExamGrade() {
        // Get the number of questions in the exam.
        System.out.print("How many questions are in the exam? ");
        int questions = Integer.parseInt(input.nextLine());

        // Get the number of questions missed.
        System.out.print("How many questions did the student miss? ");
        int missed = Integer.parseInt(input.nextLine());

        // Get the minimum passing score.
        System.out.print("What is the minimum passing score? ");
        double minPassingScore = Double.parseDouble(input.nextLine());

        // Create a PassFailExam object.
        PassFailExam exam = new PassFailExam(questions, missed, minPassingScore);

        // Display the test results.
        System.out.printf("Each question counts %.2f points.%n", exam.getPointsEach());
        System.out.printf("The exam score is %.2f%n", exam.getScore());
        System.out.printf("The exam grade is %c%n", exam.getGrade());

    }

    private void calculateExamGrade() {
        int question = 0; // Example value
        int missed = 0; // Example value

        // Get the number of questions in the exam.
        System.out.print("How many questions are in the exam? ");
        question = Integer.parseInt(input.nextLine());

        // Get the number of questions missed.
        System.out.print("How many questions did the student miss? ");
        missed = Integer.parseInt(input.nextLine());

        // Create an Exam object 
        Exam exam = new Exam(question, missed);
        exam.calculateScore();

        // Display the test results
        System.out.println("Each question counts " + exam.getPointsEach() + " points.");
        System.out.println("The exam score is " + exam.getScore());
        System.out.println("The exam grade is " + exam.getGrade());
    }

    private void registerStudent() {
        boolean validFirstName;
        boolean validLastName;
        boolean validAddress;
        boolean validPhoneNumber;
        String firstName = "";
        String lastName = "";
        String address = "";
        String phoneNumber = "";

        System.out.println("Student Registration System");
        // use do while loop to validate the first name
        do {

            System.out.println("Enter Student First Name:");
            firstName = this.input.nextLine();
            validFirstName = Validator.validateName(firstName);
        } while (validFirstName == false);
        // use do while loop to validate the last name
        do {
            System.out.println("Enter Student Last Name:");
            lastName = this.input.nextLine();
            validLastName = Validator.validateName(lastName);
        } while (validLastName == false);
        // use do while loop to validate the address
        do {
            System.out.println("Enter Student Address:");
            address = this.input.nextLine();
            validAddress = Validator.validateAddress(address);
        } while (validAddress == false);
        // use do while loop to validate the phone number
        do {
            System.out.println("Enter Student Phone Number: (xxx-xxx-xxxx)");
            phoneNumber = this.input.nextLine();
            validPhoneNumber = Validator.validatePhoneNumber(phoneNumber);
        } while (validPhoneNumber == false);
        int studentId = generateStudentId();
        Student student = new Student(firstName, lastName, address, phoneNumber, studentId);
        this.students.add(student);
        System.out.println("Student Registered Successfully.");
        System.out.println("First Name: " + student.getFirstName() + ",Last Name:  " + student.getLastName()
                + ", Address: " + student.getAddress() + ", Phone Number: " + student.getPhoneNumber()
                + ", Student ID: " + student.getStudentId());
    }

    /* Generate a random student ID
         * @return student ID
     */
    private int generateStudentId() {
        final int[] studentId = {0};
        boolean validStudentId;
        do {
            studentId[0] = (int) (Math.random() * 900) + 100;
            validStudentId = Validator.validateStudentId(studentId[0]);
        } while (validStudentId == false);
        return studentId[0];
    }

    private Student findStudentById(int studentId) {

        for (Student student : students) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }

    private Student findStudentByIdFromFile(int studentId) {
        try (BufferedReader br = new BufferedReader(new FileReader("data\\student_information.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(","); //comma-separated values
                if (fields.length == 5) { // Adjust the number of fields based on the Student class
                    int id = Integer.parseInt(fields[4]);
                    if (id == studentId) {
                        String firstName = fields[0];
                        String lastName = fields[1];
                        String address = fields[2];
                        String phoneNumber = fields[3];
                        return new Student(firstName, lastName, address, phoneNumber, id);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading student information file: " + e.getMessage());
        }
        return null; // Return null if student not found
    }

    private void unregisterStudent() {
        Student studentToRemove = selectStudent();

        if (studentToRemove != null) {
            String name = studentToRemove.getFirstName() + " " + studentToRemove.getLastName();
            int id = studentToRemove.getStudentId();
            this.students.remove(studentToRemove);
            System.out.println("ID: " + id + " " + name + " unregistered successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private Student selectStudent() {
        Student studentToSelect = null;
        boolean foundStudent = false;
        int studentId;
        do {
            System.out.println("Enter student ID");
            String studentIdInput = this.input.nextLine();

            try {
                studentId = Integer.parseInt(studentIdInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid numeric student ID.");
                continue;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid numeric student ID.");
                continue;
            }

            for (Student student : this.students) {
                if (student.getStudentId() == studentId) {
                    foundStudent = true;
                    studentToSelect = student;
                    break;
                }
            }
        } while (!foundStudent);
        return studentToSelect;
    }

    private void updateStudentInformation() {
        Student studentToUpdate = selectStudent();
        if (studentToUpdate != null) {
            boolean validChoice;
            int choice = 0;
            do {
                System.out.println("Please select an option:");
                System.out.println("1. Update Student First Name");
                System.out.println("2. Update Student Last Name");
                System.out.println("3. Update Student Address");
                System.out.println("4. Update Student Phone Number");
                System.out.println("5. Return to Main Menu");
                try {
                    choice = Integer.parseInt(this.input.nextLine());
                    validChoice = Validator.validateNumberRange(choice, 1, 5);
                    if (validChoice == false) {
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid numeric choice.");
                    validChoice = false;
                } catch (Exception e) {
                    System.out.println("An error occurred. Please try again.");
                    validChoice = false;
                }
            } while (!validChoice);

            switch (choice) {
                case 1 -> {
                    updateStudentFirstName();
                    saveStudentsToFile();
                }
                case 2 -> {
                    updateStudentLastName();
                    saveStudentsToFile();
                }
                case 3 -> {
                    updateStudentAddress();
                    saveStudentsToFile();
                }
                case 4 -> {
                    updateStudentPhoneNumber();
                    saveStudentsToFile();
                }
                case 5 -> {
                    System.out.println("Returning to the main menu...");
                    System.out.println();
                    return;
                }
                default -> {
                    System.out.println("Invalid choice.");
                    System.out.println();
                }
            }
        }
    }

    /**
     * Updates the first name of the given student. Prompts the user to enter a
     * valid first name and validates it. If the entered first name is valid, it
     * updates the student's first name.
     */
    private void updateStudentFirstName() {
        System.out.println("Update Student First Name");
        System.out.println("Enter Student ID:");
        int studentId = Integer.parseInt(this.input.nextLine().trim());

        Student student = findStudentById(studentId);
        // prompt the user for a new first name
        if (student != null) {
            boolean validFirstName;
            String firstName;
            do {
                System.out.println("Enter Student New First Name:");
                firstName = this.input.nextLine().trim();
                validFirstName = Validator.validateName(firstName);
            } while (!validFirstName);
            student.setFirstName(firstName);
            System.out.println("First Name updated successfully.");
            System.out.println("Updated First Name: " + firstName);
            System.out.println();
        } else {
            System.out.println("Student ID not found.");
        }
    }

    /**
     * Updates the last name of the given student. Prompts the user to enter a
     * valid last name and validates the input. If the input is valid, updates
     * the student's last name and confirms the update.
     */
    private void updateStudentLastName() {
        System.out.println("Enter Student ID:");
        int studentId = Integer.parseInt(this.input.nextLine().trim());

        Student student = findStudentById(studentId);
        // prompt the user for a new last name
        if (student != null) {
            boolean validLastName;
            String lastName;
            do {
                System.out.println("Enter Student New Last Name:");
                lastName = this.input.nextLine().trim();
                validLastName = Validator.validateName(lastName);
            } while (!validLastName);
            student.setLastName(lastName);
            System.out.println("Last Name updated successfully.");
            System.out.println("Updated Last Name: " + lastName);
            System.out.println();
        } else {
            System.out.println("Student ID not found.");
        }
    }
    /**
     * Updates the address of the given student. Prompts the user to enter a
     * valid address and validates it. If the address is valid, it updates the
     * student's address.
     */
    private void updateStudentAddress() {
        System.out.println("Enter Student ID:");
        int studentId = Integer.parseInt(this.input.nextLine().trim());

        Student student = findStudentById(studentId);
        // prompt the user for a new address
        if (student != null) {
            boolean validAddress;
            String address;
            do {
                System.out.println("Enter Student New Address:");
                address = this.input.nextLine().trim();
                validAddress = Validator.validateAddress(address);
            } while (!validAddress);
            student.setAddress(address);
            System.out.println("Address updated successfully.");
            System.out.println("Updated address: " + address);
            System.out.println();
        } else {
            System.out.println("Student ID not found.");
        }
    }      
    /**
     * Updates the phone number of the given student. Prompts the user to enter
     * a new phone number and validates it. If the phone number is valid, it
     * sets the new phone number for the student.
     */
    private void updateStudentPhoneNumber() {
        System.out.println("Enter Student ID:");
        int studentId = Integer.parseInt(this.input.nextLine().trim());

        Student student = findStudentById(studentId);
        // prompt the user for a new phone number
        if (student != null) {
            boolean validPhoneNumber;
            String phoneNumber;
            do {
                System.out.println("Enter Student New Phone Number:");
                phoneNumber = this.input.nextLine().trim();
                validPhoneNumber = Validator.validatePhoneNumber(phoneNumber);
            } while (!validPhoneNumber);
            student.setPhoneNumber(phoneNumber);;
            System.out.println("Phone Number updated successfully.");
            System.out.println("Updated Phone Number: " + phoneNumber);
            System.out.println();
        } else {
            System.out.println("Student ID not found.");
        }
    }
    public void Reports() {
        String[] studentNames = {"Gary,Doe", "Julia,Smith", "Bob,Brown", "Charlie,Davis",
            "Diana,Evans", "Eve,Wilson", "Frank,Miller", "Grace,Lee", "Henry,Clark",
            "John,Smit", "Ana,King", "Alice,Johnson"};
        String[] studentIds = {"101", "102", "104", "105", "106", "107", "108", "109", "110", "993", "861", "263"};
        String[] courseNames = {"College Algebra", "Introduction to Chemistry", "Physical Chemistry", "Genetics",
            "Human Anatomy", "Microbiology", "Introduction to Programming", "Algorithms", "Data Structures",
            "Web Development", "Mobile Development", "Data Structures", "Operating Systems", "Database Systems",
            "Computer Networks", "Software Engineering", "Artificial Intelligence", "Machine Learning", "Cyber Security", "Cloud Computing"};
        int[] GaryDoeGrade = {90, 75, 91, 68, 83, 85, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] JuliaSmithGrade = {85, 90, 75, 78, 81, 85, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] BobBrownGrade = {95, 85, 95, 85, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] CharlieDavisGrade = {70, 80, 83, 85, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] DianaEvansGrade = {90, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 75, 85, 70, 80, 85, 0, 0, 0};
        int[] EveWilsonGrade = {85, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, 75, 78, 81, 85, 0, 0, 0};
        int[] FrankMillerGrade = {95, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 95, 85, 95, 85, 0, 0, 0, 0};
        int[] GraceLeeGrade = {70, 0, 0, 0, 0, 0, 0, 0, 81, 95, 78, 0, 0, 0, 85, 0, 92, 0, 0, 0};
        int[] HenryClarkGrade = {90, 0, 0, 0, 0, 0, 0, 84, 0, 91, 0, 0, 78, 81, 85, 0, 0, 88, 96, 0};
        int[] JohnSmitGrade = {85, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, 86, 85, 78, 81, 0, 0, 0};
        int[] AnaKingGrade = {95, 0, 0, 0, 0, 0, 0, 85, 89, 94, 83, 77, 92, 93, 85, 79, 82, 0, 0, 0};
        int[] AliceJohnsonGrade = {70, 93, 89, 94, 74, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[][] grades = {GaryDoeGrade, JuliaSmithGrade, BobBrownGrade, CharlieDavisGrade, DianaEvansGrade, EveWilsonGrade, FrankMillerGrade, GraceLeeGrade, HenryClarkGrade, JohnSmitGrade, AnaKingGrade, AliceJohnsonGrade};

        // Calculate and display the average grades for each student 
        double[] averageGrades = new double[studentNames.length];
        for (int i = 0; i < studentNames.length; i++) {
            int coursesTaken = 0;
            int highestGrade = 0;
            int lowestGrade = 0;
            int totalGrade = 0;
            for (int j = 0; j < courseNames.length; j++) {
                totalGrade += grades[i][j];
                if (grades[i][j] > highestGrade) {
                    highestGrade = grades[i][j];
                    coursesTaken++;
                }
                averageGrades[i] = (double) totalGrade / courseNames.length;
            }
        }
        // Display the highest grade for each subject
        int[] highestGrades = new int[courseNames.length];
        for (int j = 0; j < courseNames.length; j++) {
            int highestGrade = 0;
            for (int k = 0; k < studentNames.length; k++) {
                if (grades[k][j] > highestGrade) {
                    highestGrade = grades[k][j];
                }
            }
            highestGrades[j] = highestGrade;
        }
        // Display the lowest grade for each subject
        int[] lowestGrades = new int[courseNames.length];
        for (int j = 0; j < courseNames.length; j++) {
            int lowestGrade = 100;
            boolean gradeFound = false;
            for (int k = 0; k < studentNames.length; k++) {
                if (grades[k][j] > 0 && grades[k][j] < lowestGrade) {
                    lowestGrade = grades[k][j];
                    gradeFound = true;
                }
            }
            lowestGrades[j] = gradeFound ? lowestGrade : 0; // If no grade found, set to 0
        }
        // Display the list of students sorted by grade
        for (int i = 0; i < studentNames.length; i++) {
            System.out.println("Student Name: " + studentNames[i]);
            System.out.println("Student ID: " + studentIds[i]);
            for (int j = 0; j < courseNames.length; j++) {
                System.out.println(courseNames[j] + ": " + grades[i][j]);
            }
            System.out.println("Average Grade: " + (int) averageGrades[i]);
            System.out.println();
        }
        // Display the highest and lowest grades for each subject
        for (int j = 0; j < courseNames.length; j++) {
            System.out.println("Highest Grade  " + courseNames[j] + ": " + (int) highestGrades[j]);
            System.out.println("Lowest Grade in " + courseNames[j] + ": " + (int) lowestGrades[j]);
            System.err.println();
        }
        // Save the report to a file
        try (PrintWriter writer = new PrintWriter(new FileWriter("student_report.txt"))) {
            for (int i = 0; i < studentNames.length; i++) {
                writer.println("Student Name: " + studentNames[i]);
                writer.println("Student ID: " + studentIds[i]);
                for (int j = 0; j < courseNames.length; j++) {
                    writer.println(courseNames[j] + ": " + grades[i][j]);
                }
                writer.println("Average Grade: " + (int) averageGrades[i]);
                writer.println();
            }
            // Write the highest and lowest grades for each subject
            for (int j = 0; j < courseNames.length; j++) {
                writer.println("Highest Grade in " + courseNames[j] + ": " + (int) highestGrades[j]);
                writer.println("Lowest Grade in " + courseNames[j] + ": " + (int) lowestGrades[j]);
            }
        } catch (IOException e) {
            System.err.println("Error writing report: " + e.getMessage());
        }
        System.out.println("Report generated successfully.");
    }
}

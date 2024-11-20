package com.tatianaworsham;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.tatianaworsham.models.Student;
import com.tatianaworsham.models.Subject;

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

            // Write the student information
            for (Student s : students) {
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
            // Write the header

            // Write the student information
            for (Subject s : subjects) {
                writer.printf("%s,%s,%s,%s,%d%n", s.getcourseName(), s.getCourseNumber(), s.getCredits(), s.getDepartment(), s.getInstructor());
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
                System.out.println("5. Enroll Student in a Course");
                System.out.println("6. Calculate Exam Grade");
                System.out.println("7. Calculate Pass/Fail Activity Grade");
                System.out.println("8. Calculate Pass/Fail Exam Grade");
                System.out.println("9. Find Student by ID");
                System.out.println("10. Exit");
                choice = Integer.parseInt(this.input.nextLine());
                validChoice = Validator.validateNumberFromOneToTen(choice);
                if (validChoice == false) {
                    System.out.println("Invalid choice. Please enter a number between 1 and 10.");
                }
            } while (validChoice == false);
            switch (choice) {

                case 1:
                    registerStudent();
                    saveStudentsToFile();
                    break;
                case 2:
                    unregisterStudent();
                    saveStudentsToFile();
                    break;
                case 3:
                    updateStudentInformation();
                    saveStudentsToFile();
                    break;
                case 4:
                    Reports();
                    break;
                case 5:
                    enrollStudentInCourse();
                    saveStudentsToFile();
                    break;
                case 6:
                    Exam exam = new Exam(100, 100);
                    exam.calculateExam();
                    break;
                case 7:
                    //PassFailActivityGrade();
                    break;
                case 8:
                    //calculatePassFailExamGrade();
                    PassFailExam passFailExam = new PassFailExam(100, 100, 70);
                    passFailExam.calculateExam();
                    break;

                case 9:

                    break;
                case 10:
                    System.out.println("Exiting...");
                    this.input.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    private void registerStudent() {
        boolean validFirstName = false;
        boolean validLastName = false;
        boolean validAddress = false;
        boolean validPhoneNumber = false;
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
        boolean validStudentId = false;
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
                case 1:
                    updateStudentFirstName();
                    saveStudentsToFile();
                    break;
                case 2:
                    updateStudentLastName();
                    saveStudentsToFile();
                    break;
                case 3:
                    updateStudentAddress();
                    saveStudentsToFile();
                    break;
                case 4:
                    updateStudentPhoneNumber();
                    saveStudentsToFile();
                    break;
                case 5:
                    System.out.println("Returning to the main menu...");
                    System.out.println();
                    return;
                default:
                    System.out.println("Invalid choice.");
                    System.out.println();
                    break;
            }
        }
    }

    /**
     * Updates the first name of the given student. Prompts the user to enter a
     * valid first name and validates it. If the entered first name is valid, it
     * updates the student's first name.
     *
     * @param studentToUpdate the student whose first name is to be updated
     */
    private void updateStudentFirstName() {
        System.out.println("Update Student First Name");
        System.out.println("Enter Student ID:");
        int studentId = Integer.parseInt(this.input.nextLine().trim());

        // Read the file and update the student's first name
        File inputFile = new File("data\\student_information.txt");
        File tempFile = new File("data\\temp_student_information.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)); PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String line;
            boolean studentFound = false;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 5) {
                    int id = Integer.parseInt(fields[4].trim());
                    if (id == studentId) {
                        studentFound = true;
                        boolean isFirstNameValid;
                        String firstName = "";
                        do {
                            System.out.println("Enter Student New First Name:");
                            firstName = this.input.nextLine().trim();
                            isFirstNameValid = Validator.validateName(firstName);
                        } while (!isFirstNameValid);
                        fields[0] = firstName;
                        System.out.println("First Name updated successfully.");
                        System.out.println("Updated First Name: " + firstName);
                        System.out.println();
                    }
                    writer.println(String.join(",", fields));
                } else {
                    writer.println(line);
                }
            }

            if (!studentFound) {
                System.out.println("Student ID not found.");
            }

        } catch (IOException e) {
            System.err.println("Error updating student information: " + e.getMessage());
        }

        // Replace the original file with the updated file
        if (!inputFile.delete()) {
            System.err.println("Could not delete original file");
            return;
        }
        if (!tempFile.renameTo(inputFile)) {
            System.err.println("Could not rename temp file");
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

        // Read the file and update the student's last name
        File inputFile = new File("data\\student_information.txt");
        File tempFile = new File("data\\temp_student_information.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)); PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String line;
            boolean studentFound = false;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 5) {
                    int id = Integer.parseInt(fields[4].trim());
                    if (id == studentId) {
                        studentFound = true;
                        boolean isLastNameValid;
                        String lastName = "";
                        do {
                            System.out.println("Enter Student New Last Name:");
                            lastName = this.input.nextLine().trim();
                            isLastNameValid = Validator.validateName(lastName);
                        } while (!isLastNameValid);
                        fields[1] = lastName;
                        System.out.println("Last Name updated successfully.");
                        System.out.println("Updated First Name: " + lastName);
                        System.out.println();
                    }
                    writer.println(String.join(",", fields));
                } else {
                    writer.println(line);
                }
            }

            if (!studentFound) {
                System.out.println("Student ID not found.");
            }

        } catch (IOException e) {
            System.err.println("Error updating student information: " + e.getMessage());
        }

        // Replace the original file with the updated file
        if (!inputFile.delete()) {
            System.err.println("Could not delete original file");
            return;
        }
        if (!tempFile.renameTo(inputFile)) {
            System.err.println("Could not rename temp file");
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

        // Read the file and update the student's address
        File inputFile = new File("data\\student_information.txt");
        File tempFile = new File("data\\temp_student_information.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)); PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String line;
            boolean studentFound = false;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 5) {
                    int id = Integer.parseInt(fields[4].trim());
                    if (id == studentId) {
                        studentFound = true;
                        boolean isAddressValid;
                        String address = "";
                        do {
                            System.out.println("Enter Student New Address: ");
                            address = this.input.nextLine().trim();
                            isAddressValid = Validator.validateAddress(address);
                        } while (!isAddressValid);
                        fields[2] = address;
                        System.out.println("Address updated successfully.");
                        System.out.println("Updated Address: " + address);
                        System.out.println();
                    }
                    writer.println(String.join(",", fields));
                } else {
                    writer.println(line);
                }
            }
            if (!studentFound) {
                System.out.println("Student ID not found.");
            }

        } catch (IOException e) {
            System.err.println("Error updating student information: " + e.getMessage());
        }

        // Replace the original file with the updated file
        if (!inputFile.delete()) {
            System.err.println("Could not delete original file");
            return;
        }
        if (!tempFile.renameTo(inputFile)) {
            System.err.println("Could not rename temp file");
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

        // Read the file and update the student's phone number
        File inputFile = new File("data\\student_information.txt");
        File tempFile = new File("data\\temp_student_information.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)); PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String line;
            boolean studentFound = false;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 5) {
                    int id = Integer.parseInt(fields[4].trim());
                    if (id == studentId) {
                        studentFound = true;
                        boolean isPhoneNumberValid;
                        String phoneNumber = "";
                        do {
                            System.out.println("Enter Student new phone number: XXX-XXX-XXXX ");
                            phoneNumber = this.input.nextLine().trim();
                            isPhoneNumberValid = Validator.validatePhoneNumber(phoneNumber);
                        } while (!isPhoneNumberValid);
                        fields[3] = phoneNumber;
                        System.out.println("Phone number updated successfully.");
                        System.out.println("Updated phone number: " + phoneNumber);
                        System.out.println();
                    }
                    writer.println(String.join(",", fields));
                } else {
                    writer.println(line);
                }
            }
            if (!studentFound) {
                System.out.println("Student ID not found.");
            }

        } catch (IOException e) {
            System.err.println("Error updating student information: " + e.getMessage());
        }

        // Replace the original file with the updated file
        if (!inputFile.delete()) {
            System.err.println("Could not delete original file");
            return;
        }
        if (!tempFile.renameTo(inputFile)) {
            System.err.println("Could not rename temp file");
        }
    }

    private void enrollStudentInCourse() {
        System.out.println("Enter Student ID:");
        int studentId = Integer.parseInt(input.nextLine());
        Student student = findStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        // Open the courses file
        File file = new File("courses.txt");
        // Read the course information from the file
        if (file.exists()) {
            try (Scanner inputFile = new Scanner(file)) {
                while (inputFile.hasNextLine()) {
                    String line = inputFile.nextLine();
                    if (!line.trim().isEmpty()) {
                        String[] parts = line.split("\\s+");
                        if (parts.length == 3) {
                            String courseName = parts[0];
                            int courseNumber = Integer.parseInt(parts[1]);
                            String instructor = parts[2];
                            System.out.printf("%-15s %-15d %-20s%n", courseName, courseNumber, instructor);
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
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

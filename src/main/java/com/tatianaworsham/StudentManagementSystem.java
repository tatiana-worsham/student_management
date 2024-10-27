package com.tatianaworsham;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementSystem {

    private ArrayList<Student> students;

    public StudentManagementSystem() {
        this.students = new ArrayList<Student>();
        this.greetUser();
    }

    private void greetUser() {
        System.out.println("Welcome to the Student Management System!");
        while (true) {
            System.out.println("Please select an option:");
            System.out.println("1. Add Student");
            System.out.println("2. View Student List");
            System.out.println("3. Get Average Grade for All Students");
            System.out.println("4. Generate Reports");
            System.out.println("5. Get Average Grade for Student");
            System.out.println("6. Get Lowest Grade for Subject");
            System.out.println("7. Get Highest Grade for Subject");
            System.out.println("8. Save Student Data to File");
            System.out.println("9. Register student for a course");
            System.out.println("10. Exit");

            Scanner input = new Scanner(System.in);
            int choice = input.nextInt();
            input.nextLine(); // Consume newline
            switch (choice) {
                // Add cases here
                case 1:
                    //addStudent(); or maybe call it registerStudent()
                    break;
                case 2:
                    printStudentList();
                    break;
                case 3:
                    getAverageGradeForAllStudents();
                    break;
                case 4:
                    generateReports();
                    break;
                case 5:
                    System.out.println("Enter student id:");
                    String stringId = input.nextLine();
                    // Convert string to integer
                    int id = Integer.parseInt(stringId);
                    getAverageGradeForStudent(id);
                    break;
                case 6:
                    System.out.println("Enter subject name:");
                    String subject = input.nextLine();
                    getLowestGradeForSubject(subject);
                    break;
                case 7:
                    System.out.println("Enter subject name:");
                    String subjectName = input.nextLine();
                    getHighestGradeForSubject(subjectName);
                    break;
                case 8:
                    // saveStudentDataToFile();
                    break;
                case 9:
                    // registerStudentForCourse();
                    break;
                case 10:
                    System.out.println("Exiting...");
                    input.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    public Student addStudent() {
        Student student = new Student();
        try {
            File file = new File("student.txt");
            PrintWriter outputFile = new PrintWriter(file);
            Scanner input = new Scanner(System.in);

            System.out.println("Enter student information:");
            
            while (true) {
                System.out.println("Enter student first name:");
                // firstName = input.nextLine();
                student.setFirstName(input.nextLine());
                System.out.println("Enter student last name:");
                student.setLastName(input.nextLine());
                System.out.println("Enter student id:");
                student.setId(input.nextInt());
                input.nextLine(); // Consume newline
                System.out.println("Enter course name:");

                outputFile.println(student.getFirstName() + " " + student.getLastName() + " " + student.getId());

                System.out.println("Do you want to enter another student? (y for yes, n for no)");
                String response = input.nextLine();
                if (!response.equalsIgnoreCase("y")) {
                    break;
                }
            }
            outputFile.close();
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error creating or writing to the file.");
            e.printStackTrace();
        }
        return student;
    }

    public void manageStudents() {
        File file = new File("student.txt");
        ArrayList<String> students = new ArrayList<String>();

        if (file.exists()) {
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    students.add(scanner.nextLine());
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Student File Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Update Student");
            System.out.println("4. View Students");
            System.out.println("5. Exit");

            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter Student name: firstName lastName");
                    String name = input.nextLine();
                    students.add(name);
                    System.out.println("Student added successfully.");
                    break;
                case 2:
                    System.out.println("Enter Student Name: firstName lastName");
                    String deleteName = input.nextLine();
                    students.remove(deleteName);
                    System.out.println("Student deleted successfully.");
                    break;
                case 3:
                    System.out.println("Enter Student Name: firstName lastName");
                    String updateName = input.nextLine();
                    System.out.println("Enter New Student Name:");
                    String newName = input.nextLine();
                    students.set(students.indexOf(updateName), newName);
                    System.out.println("Student updated successfully.");
                    break;
                case 4:
                    System.out.println("Student Records:");
                    for (String student : students) {
                        System.out.println(student);
                    }
                    break;
                case 5:
                    input.close();
                    return;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }

    public void printStudentList() {
        for (Student student : this.students) {
            System.out.println("First Name: " + student.getFirstName() + " Last Name: " + student.getLastName() + ", ID: " + student.getId());
        }
    }

    public void getAverageGradeForAllStudents() {
        int totalGrade = 0;
        for (Student student : this.students) {
            // Todo: Implement this
            // iterate over each student
            // in a inner loop iterate over each course in the student's courses property
            // sum up the grades
            // divide by the number of courses to calculate the average 
        }
        double averageGrade = (double) totalGrade / students.size();
        System.out.println("Average Grade: " + averageGrade);
    }

    public void generateReports() {
        for (Student student : this.students) {
            System.out.println("Name: " + student.getFirstName() + student.getLastName() + ", ID: " + student.getId());
            // Todo: Implement this
            // to get grades you would have to access teh student's courses property.  Because the grade is a property of the Subject class
            // System.out.println("Grades: " + student.getGrades());
        }
    }

    public void getAverageGradeForStudent(int id) {
        for (Student student : this.students) {
            if (student.getId() == id) {
                // Todo: Implement this
                // To get the grades you must access the student's courses property

                // ArrayList<Integer> grades = student.getGrades();
                // int totalGrade = 0;
                // for (int grade : grades) {
                //     totalGrade += grade;
                // }
                // double averageGrade = (double) totalGrade / grades.size();
                // System.out.println("Average Grade for " + name + ": " + averageGrade);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void getLowestGradeForSubject(String subject) {
        int lowestGrade = 100;
        for (Student student : this.students) {
            ArrayList<Subject> subjects = student.getSubjects();
            for (Subject s : subjects) {
                if (s.getcourseName().equals(subject) && s.getGrade() < lowestGrade) {
                    lowestGrade = s.getGrade();
                }
            }
        }
        System.out.println("Lowest Grade for " + subject + ": " + lowestGrade);
    }

    public void getHighestGradeForSubject(String subject) {
        int highestGrade = 0;
        for (Student student : students) {
            ArrayList<Subject> subjects = student.getSubjects();
            for (Subject s : subjects) {
                if (s.getcourseName().equals(subject) && s.getGrade() > highestGrade) {
                    highestGrade = s.getGrade();
                }
            }
        }
        System.out.println("Highest Grade for " + subject + ": " + highestGrade);
    }
}

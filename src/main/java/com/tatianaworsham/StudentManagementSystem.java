package com.tatianaworsham;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.tatianaworsham.models.Student;
import com.tatianaworsham.models.Subject;

public class StudentManagementSystem {

    private ArrayList<Student> students;

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
        this.greetUser();
    }

    private void greetUser() {
        System.out.println("Welcome to the Student Management System!");

        while (true) {
            try (Scanner input = new Scanner(System.in)) {
                boolean validChoice;
                int choice = 0;
                do {
                    System.out.println("Please select an option:");
                    System.out.println("1. Register a Student");
                    System.out.println("2. View Student List");
                    System.out.println("3. Get Average Grade for All Students");
                    System.out.println("4. Generate Reports");
                    System.out.println("5. Get Average Grade for Student");
                    System.out.println("6. Get Lowest Grade for Subject");
                    System.out.println("7. Get Highest Grade for Subject");
                    System.out.println("8. Save Student Data to File");
                    System.out.println("9. Enroll Student in a Course");
                    System.out.println("10. Exit");
                    choice = input.nextInt();
                    validChoice = Validator.validateNumberFromOneToTen(choice);
                    if (validChoice == false) {
                        System.out.println("Invalid choice. Please enter a number between 1 and 10.");
                    }
                } while (validChoice == false);

                switch (choice) {
                    // Add cases here
                    case 1:
                        Student registeredStudent = this.registerStudent();
                        this.students.add(registeredStudent);
                        // print the list of students
                        System.out.println("List of students:");
                        for (Student student : this.students) {
                            System.out.println("First Name: " + student.getFirstName() + " Last Name: " + student.getLastName() + ", ID: " + student.getStudentId());
                        }
                        // The registration requires the student to enter their first name, last name, addresss, and phone number
                        // it will seve the information to the file " studentRegistration.txt".
                        // It will create student ID (random number of 3 digits and return that student Id to the user.
                        // The student ID will be used to register for courses.
                        // The student can register for multiple courses.
                        // The student can view the courses they are registered for.
                        // The student can unregister for a course.
                        // The student can view their grades.
                        // The student can view their average grade.
                        // The student can view the highest grade.
                        // The student can view the lowest grade.
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
                        // Enroll student in a course
                        System.out.println("Enter student id:");
                        String studentId = input.nextLine();
                        // find student by id from this.students ArrayList
                        Student student = null;
                        for (Student s : this.students) {
                            if (s.getStudentId() == Integer.parseInt(studentId)) {
                                student = s;
                                break;
                            }
                        }
                        student = enrollStudentInCourse(student);
                        // confirm we see the list of courses teh student is enrolled in
                        System.out.println("Enrolled student: " + student);
                        System.out.println("Courses for " + student);
                        for (Subject subj : student.getSubjects()) {
                            System.out.println(subj.getcourseName());
                        }
                        break;
                    case 10:
                        System.out.println("Exiting...");
                        input.close();
                        return;
                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public Student registerStudent() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Student First Name:");
        String firstName = input.nextLine();
        System.out.println("Enter Student Last Name:");
        String lastName = input.nextLine();
        System.out.println("Enter Student Address:");
        String address = input.nextLine();
        System.out.println("Enter Student Phone Number:");
        String phoneNumber = input.nextLine();
        int studentId = (int) (Math.random() * 900) + 100;
        Student student = new Student(firstName, lastName, address, phoneNumber, studentId);
        this.students.add(student);
        System.out.println("Student Registered Successfully.");
        // print out the registered student's information

        System.out.println("");
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
            System.out.println("First Name: " + student.getFirstName() + " Last Name: " + student.getLastName() + ", ID: " + student.getStudentId());
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
            System.out.println("Name: " + student.getFirstName() + student.getLastName() + ", ID: " + student.getStudentId());
            // Todo: Implement this
            // to get grades you would have to access teh student's courses property.  Because the grade is a property of the Subject class
            // System.out.println("Grades: " + student.getGrades());
        }
    }

    public void getAverageGradeForStudent(int id) {
        for (Student student : this.students) {
            if (student.getStudentId() == id) {
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

    public Student enrollStudentInCourse(Student student) {
        // Prompt user for student data and then create and return an instance of the subject class
        boolean validGrade = false;
        boolean validCourseName = false;
        String courseName;
        int grade;
        int courseNumber;

        try (Scanner input = new Scanner(System.in)) {
            do {
                System.out.println("Enter Course Name:");
                courseName = input.nextLine();
                validCourseName = Validator.validateCourseName(courseName);
            } while (validCourseName == false);

            // use do while loop to validate the coure number
            System.out.println("Enter Course Number:");
            courseNumber = input.nextInt();

            do {
                System.out.println("Enter Grade as numeric value (0-100):");
                grade = input.nextInt();
                validGrade = Validator.validateGrade(grade);
            } while (validGrade == false);

            Subject subject = new Subject(courseName, courseNumber, grade);
            student.addSubject(subject);
            return student;
        }
    }
}

package com.tatianaworsham;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.tatianaworsham.models.Student;

public class StudentLoader {
    public static ArrayList<Student> loadStudents(String filePath) {
        ArrayList<Student> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(","); // comma-separated values
                if (fields.length == 5) { // Assuming 5 fields per line
                    String firstName = fields[0].trim();
                    String lastName = fields[1].trim();
                    String address = fields[2].trim();
                    String phoneNumber = fields[3].trim();
                    int studentId = Integer.parseInt(fields[4].trim());
                    students.add(new Student(firstName, lastName, address, phoneNumber, studentId));
                }
            }
        } catch (IOException e) {
            Logger.getLogger(StudentLoader.class.getName()).log(Level.SEVERE, null, e);
        }

        return students;
    }
}
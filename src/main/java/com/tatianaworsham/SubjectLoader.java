package com.tatianaworsham;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.tatianaworsham.models.Subject;
public class SubjectLoader {
    public static ArrayList<Subject> loadSubjects(String filePath) {
        ArrayList<Subject> subjects = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(","); // Assuming comma-separated values
                if (fields.length == 5) { // Adjust the number of fields based on the Subject class
                    String courseName = fields[0];
                    int courseNumber = Integer.parseInt(fields[1]);
                    int credits = Integer.parseInt(fields[2]);
                    String department = fields[3];
                    String Instructor = fields[4];
                    
                }
            }
        } catch (IOException e) {
            Logger.getLogger(SubjectLoader.class.getName()).log(Level.SEVERE, null, e);
        }

        return subjects;
    }

}

package com.tatianaworsham.models;

import java.util.Scanner;

import com.tatianaworsham.Exam;

public class GradeAction {
    private int numQuestions;
    private int numMissed;
    private double pointsEach;
    private double score;

    public GradeAction() {
        this.numQuestions = 0;
        this.numMissed = 0;
        this.pointsEach = 0.0;
        this.score = 0.0;
    }

    // Getter and Setter for numQuestions
    public int getNumQuestions() {
        return numQuestions;
    }

    public void setNumQuestions(int numQuestions) {
        this.numQuestions = numQuestions;
    }

    // Getter and Setter for numMissed
    public int getNumMissed() {
        return numMissed;
    }

    public void setNumMissed(int numMissed) {
        this.numMissed = numMissed;
    }

    // Getter and Setter for pointsEach
    public double getPointsEach() {
        return pointsEach;
    }

    public void setPointsEach(double pointsEach) {
        this.pointsEach = pointsEach;
    }

    /**
     * The setScore method sets the score field.
     *
     * @param score
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * The getScore method returns the score field.
     *
     * @return
     */
    public double getScore() {
        return score;
    }

    /**
     * The getGrade method returns the grade based on the score.
     *
     * @return
     */
    public char getGrade() {
        if (score >= 90) {
            return 'A';
        } else if (score >= 80) {
            return 'B';
        } else if (score >= 70) {
            return 'C';
        } else if (score >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    public void calculateExam() {
        double numericScore;

        // Create a scanner object to read input.
        Scanner keyboard = new Scanner(System.in);

        // Get the number of questions in the exam.
        System.out.print("How many questions are in the exam?");
        setNumQuestions(Integer.parseInt(keyboard.nextLine()));

        // Get the number of questions missed.
        System.out.print("How many questions did the student miss?");
        setNumMissed(Integer.parseInt(keyboard.nextLine()));

        // Calculate the points for each question and the numeric score.
        this.pointsEach = 100.0 / getNumQuestions();
        numericScore = 100.0 - (getNumMissed() * getPointsEach());

        // Call the superclass's setScore method to set the numeric score.
        setScore(numericScore);

        // Create an Exam object.
        Exam exam = new Exam(getNumQuestions(), getNumMissed());

        // Display the test results.
        System.out.printf("Each question counts %.2f points.%n", exam.getPointsEach());
        System.out.printf("The exam score is %.2f%n", exam.getScore());
        System.out.printf("The exam grade is %c%n", exam.getGrade());
        keyboard.close();
    }
}

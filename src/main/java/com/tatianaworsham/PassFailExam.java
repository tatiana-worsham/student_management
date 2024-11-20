package com.tatianaworsham;

import java.util.Scanner;

import com.tatianaworsham.models.GradeAction;

/**
 * This class calculates the grade for a pass/fail exam.
 */
public class PassFailExam extends GradeAction {
    // Call the superclass constructor.

    private int questions;
    private int missed;
    private double minPassingScore;
    private double pointsEach;

    public PassFailExam(int questions, int missed, double minPassingScore) {

        this.questions = questions;
        this.missed = missed;
        this.minPassingScore = minPassingScore;
        this.pointsEach = 100.0 / questions;

        // Declare a variable to hold the numeric score.
        double numericScore;

        // Calculate the points for each question and the numeric score.
        pointsEach = 100.0 / questions;
        numericScore = 100.0 - (missed * pointsEach);

        // Call the superclass's setScore method to set the numeric score.
        setScore(numericScore);

        // Create a scanner object to read input.
        Scanner keyboard = new Scanner(System.in);

        // Get the number of questions in the exam.
        System.out.print("How many questions are in the exam?");
        questions = Integer.parseInt(keyboard.nextLine());

        // Get the number of questions missed.
        System.out.print("How many questions did the student miss?");
        missed = Integer.parseInt(keyboard.nextLine());

        // Get the minimum passing score.
        System.out.print("What is the minimum passing score?");
        minPassingScore = Double.parseDouble(keyboard.nextLine());

        // Create a PassFailExam object.
        PassFailExam exam = new PassFailExam(questions, missed, minPassingScore);

        // Display the test results.
        System.out.printf("Each question counts %.2f points.%n", exam.getPointsEach());
        System.out.printf("The exam score is %.2f%n", exam.getScore());
        System.out.printf("The exam grade is %c%n", exam.getGrade());
        keyboard.close();
    }

    /**
     * The getPointsEach method returns the number of points each questions
     * worth. /@ returt the value in the pointsEach field
     */
    public double getPointsEach() {
        return pointsEach;
    }

    /**
     * The getNumMissed method returns the number of questions missed.
     *
     * @return The value in the numMissed field.
     */
    public int getNumMissed() {
        return missed;
    }

    @Override
    public char getGrade() {
        if (super.getScore() >= minPassingScore) {
            return 'P';
        } else {
            return 'F';
        }
    }
}

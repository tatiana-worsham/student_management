package com.tatianaworsham;

import com.tatianaworsham.models.GradeAction;

/**
 * This class calculates the grade for a pass/fail exam.
 */
public class PassFailExam extends GradeAction {
    private final int questions;
    private final int missed;
    private final double minPassingScore;
    private final double pointsEach;

    public PassFailExam(int questions, int missed, double minPassingScore) {
        this.questions = questions;
        this.missed = missed;
        this.minPassingScore = minPassingScore;
        this.pointsEach = 100.0 / questions;

        // Calculate the points for each question and the numeric score.
        double numericScore = 100.0 - (missed * pointsEach);

        // Call the superclass's setScore method to set the numeric score.
        setScore(numericScore);
    }

    
    /**
     * The getPointsEach method returns the number of points each question is worth.
     *
     * @return The value in the pointsEach field.
     */
    @Override
    public double getPointsEach() {
        return pointsEach;
    }

    /**
     * The getNumMissed method returns the number of questions missed.
     *
     * @return The value in the missed field.
     */
    @Override
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
package com.tatianaworsham;

import com.tatianaworsham.models.GradeAction;

public class Exam extends GradeAction {
    private int numQuestions;
    private int numMissed;
    private double pointsEach;

    public Exam(int questions, int missed) {
        this.numQuestions = questions;
        this.numMissed = missed;
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
        
    // The adjustScore method adjusts the score by 0.5 points.
    //if the score is within 0.5 points of the next whole number.
    private void adjustScore() {
        double fraction = getScore() - (int) getScore();
        if (fraction >= 0.5) {
            setScore(getScore() + (1.0 - fraction));
        }
    }
}
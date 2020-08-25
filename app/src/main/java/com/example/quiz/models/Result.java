package com.example.quiz.models;

public class Result {

    private String category;
    private int correctAnswers;
    private String difficulty;
    private int time;

    public Result(String category, int correctAnswers, String difficulty, int time) {
        this.category = category;
        this.correctAnswers = correctAnswers;
        this.difficulty = difficulty;
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}

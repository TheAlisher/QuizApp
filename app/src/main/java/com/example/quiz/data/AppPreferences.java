package com.example.quiz.data;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {

    private String PREF_QUESTIONS_SIZE = "questions_size";
    private String PREF_CATEGORY = "category";
    private String PREF_DIFFICULTY = "difficulty";
    private String PREF_CORRECT_ANSWERS = "correct_answers";

    private SharedPreferences preferences;

    public AppPreferences(Context context) {
        preferences = context.getSharedPreferences("quiz_preferences", Context.MODE_PRIVATE);
    }

    public int questionsSize() {
        return preferences.getInt(PREF_QUESTIONS_SIZE, 0);
    }

    public void setQuestionSize(int size) {
        preferences.edit().putInt(PREF_QUESTIONS_SIZE, size).apply();
    }

    public String questionsCategory() {
        return preferences.getString(PREF_CATEGORY, "");
    }

    public void setQuestionCategory(String category) {
        preferences.edit().putString(PREF_CATEGORY, category).apply();
    }

    public String questionsDifficulty() {
        return preferences.getString(PREF_DIFFICULTY, "");
    }

    public void setQuestionDifficulty(String difficulty) {
        preferences.edit().putString(PREF_DIFFICULTY, difficulty).apply();
    }

    public int questionCorrectAnswers() {
        return preferences.getInt(PREF_CORRECT_ANSWERS, 0);
    }

    public void setQuestionCorrectAnswers(int correctAnswers) {
        preferences.edit().putInt(PREF_CORRECT_ANSWERS, correctAnswers).apply();
    }
}

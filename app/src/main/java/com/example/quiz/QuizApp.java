package com.example.quiz;

import android.app.Application;

import com.example.quiz.data.remote.IQuizAPIClient;
import com.example.quiz.data.remote.QuizAPIClient;

public class QuizApp extends Application {

    public static IQuizAPIClient quizAPIClient;

    @Override
    public void onCreate() {
        super.onCreate();

        quizAPIClient = new QuizAPIClient();
    }
}

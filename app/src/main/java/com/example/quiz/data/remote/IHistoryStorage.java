package com.example.quiz.data.remote;

import androidx.lifecycle.LiveData;

import com.example.quiz.models.QuizResult;

import java.util.List;

public interface IHistoryStorage {

    QuizResult getQuizResult(int id);

    int saveQuizResult(QuizResult quizResult);

    LiveData<List<QuizResult>> getAll();

    void delete(int id);

    void deleteAll();
}

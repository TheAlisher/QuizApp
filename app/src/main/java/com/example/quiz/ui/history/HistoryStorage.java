package com.example.quiz.ui.history;

import androidx.lifecycle.LiveData;

import com.example.quiz.data.remote.IHistoryStorage;
import com.example.quiz.models.QuizResult;

import java.util.List;

public class HistoryStorage implements IHistoryStorage {

    @Override
    public QuizResult getQuizResult(int id) {
        return null;
    }

    @Override
    public int saveQuizResult(QuizResult quizResult) {
        return 0;
    }

    @Override
    public LiveData<List<QuizResult>> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void deleteAll() {

    }
}

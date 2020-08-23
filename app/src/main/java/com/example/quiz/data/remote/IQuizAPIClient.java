package com.example.quiz.data.remote;

import com.example.quiz.models.Question;

import java.util.List;

public interface IQuizAPIClient {

    void getQuestions(
            int amount,
            String category,
            String difficulty,
            QuestionsCallback callback);

    interface QuestionsCallback {

        void onSuccess(List<Question> questions);

        void onFailure(Exception exception);
    }
}

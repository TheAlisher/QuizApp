package com.example.quiz.data.remote;

import com.example.quiz.core.IBaseCallback;
import com.example.quiz.models.Question;

import java.util.List;

public interface IQuizAPIClient {

    void getQuestions(
            int amount,
            String category,
            String difficulty,
            QuestionsCallback callback);

    interface QuestionsCallback extends IBaseCallback<List<Question>> {

        @Override
        void onSuccess(List<Question> result);

        @Override
        void onFailure(Exception exception);
    }
}

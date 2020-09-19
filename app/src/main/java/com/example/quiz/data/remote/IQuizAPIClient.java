package com.example.quiz.data.remote;

import com.alis.core.IBaseCallback;
import com.example.quiz.models.Question;

import java.util.List;

public interface IQuizAPIClient {

    void getQuestions(
            int amount,
            int category,
            String difficulty,
            QuestionsCallback callback);

    interface QuestionsCallback extends IBaseCallback<List<Question>> {

        @Override
        void onSuccess(List<Question> result);

        @Override
        void onFailure(Exception exception);
    }
}

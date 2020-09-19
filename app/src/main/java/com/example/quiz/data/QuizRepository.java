package com.example.quiz.data;

import androidx.lifecycle.LiveData;

import com.example.quiz.data.remote.IHistoryStorage;
import com.example.quiz.data.remote.IQuizAPIClient;
import com.example.quiz.db.QuizDao;
import com.example.quiz.models.Question;
import com.example.quiz.models.QuizResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizRepository implements IHistoryStorage, IQuizAPIClient {

    private IQuizAPIClient quizAPIClient;
    private IHistoryStorage historyStorage;
    private QuizDao quizDao;

    public QuizRepository(IQuizAPIClient quizAPIClient, IHistoryStorage historyStorage, QuizDao quizDao) {
        this.quizAPIClient = quizAPIClient;
        this.historyStorage = historyStorage;
        this.quizDao = quizDao;
    }

    private Question shuffleAnswer(Question question) {
        ArrayList<String> answers = new ArrayList<>();
        answers.add(question.getCorrectAnswers());
        answers.addAll(question.getIncorrectAnswers());
        Collections.shuffle(answers);
        question.setAnswers(answers);
        return question;
    }

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

    @Override
    public void getQuestions(int amount, int category, String difficulty, QuestionsCallback callback) {
        quizAPIClient.getQuestions(
                amount,
                category,
                difficulty,
                new QuestionsCallback() {
                    @Override
                    public void onSuccess(List<Question> result) {
                        for (int i = 0; i < result.size(); i++) {
                            result.set(i, shuffleAnswer(result.get(i)));
                        }

                        callback.onSuccess(result);
                    }

                    @Override
                    public void onFailure(Exception exception) {

                    }
                });
    }
}

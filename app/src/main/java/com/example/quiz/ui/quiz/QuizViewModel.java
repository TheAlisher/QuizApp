package com.example.quiz.ui.quiz;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quiz.QuizApp;
import com.example.quiz.data.remote.IQuizAPIClient;
import com.example.quiz.models.Question;

import java.util.List;

public class QuizViewModel extends ViewModel {

    MutableLiveData<List<Question>> questions = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();

    private IQuizAPIClient quizAPIClient = QuizApp.quizAPIClient;
    private List<Question> mQuestions;

    void init(int amount, String category, String difficulty) {
        quizAPIClient.getQuestions(
                amount,
                category,
                difficulty,
                new IQuizAPIClient.QuestionsCallback() {
                    @Override
                    public void onSuccess(List<Question> result) {
                        mQuestions = result;
                        questions.setValue(result);
                        currentQuestionPosition.setValue(0);
                    }

                    @Override
                    public void onFailure(Exception exception) {

                    }
                });
    }

    void finishQuiz() {

    }

    void onSkipClick() {

    }

    void onBackPressed() {

    }

    public void onAnswerClick(int position, int selectAnswerQuestion) {
        if (mQuestions.size() > position && position >= 0) {
            mQuestions.get(position).setSelectAnswerPosition(selectAnswerQuestion);
            questions.setValue(mQuestions);
            if (position == mQuestions.size() - 1) {
                finishQuiz();
            } else {
                currentQuestionPosition.setValue(currentQuestionPosition.getValue() + 1);
            }
        }
    }
}

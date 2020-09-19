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
    MutableLiveData<Boolean> finish = new MutableLiveData<>();
    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    private List<Question> mQuestions;

    void init(int amount, int category, String difficulty) {
        isLoading.setValue(true);
        QuizApp.repository.getQuestions(
                amount,
                category,
                difficulty,
                new IQuizAPIClient.QuestionsCallback() {
                    @Override
                    public void onSuccess(List<Question> result) {
                        mQuestions = result;
                        questions.setValue(result);
                        currentQuestionPosition.setValue(0);
                        isLoading.setValue(false);
                    }

                    @Override
                    public void onFailure(Exception exception) {

                    }
                });
    }

    public void onAnswerClick(int position, int selectAnswerQuestion) {
        if (mQuestions.size() > position && position >= 0) {
            mQuestions.get(position).setSelectAnswerPosition(selectAnswerQuestion);
            questions.setValue(mQuestions);
            if (position == mQuestions.size() - 1) {
                finishQuiz();
            } else {
                nextQuestion();
            }
        }
    }

    private void nextQuestion() {
        currentQuestionPosition.setValue(currentQuestionPosition.getValue() + 1);
    }

    void finishQuiz() {
        finish.setValue(true);
    }

    void onSkipClick() {
        nextQuestion();
    }

    void onBackPressed() {
        if (currentQuestionPosition.getValue() != null && currentQuestionPosition.getValue() > 0) {
            currentQuestionPosition.setValue(currentQuestionPosition.getValue() - 1);
        }
    }
}

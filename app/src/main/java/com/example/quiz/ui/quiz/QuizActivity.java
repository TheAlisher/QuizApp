package com.example.quiz.ui.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.quiz.QuizApp;
import com.example.quiz.R;
import com.example.quiz.data.remote.IQuizAPIClient;
import com.example.quiz.models.Question;

import java.util.List;

public class QuizActivity extends AppCompatActivity {

    public static final String EXTRA_SLIDER_VALUES = "slider_value";
    public static final String EXTRA_SPINNER_CATEGORY_VALUES = "spinner_category_value";
    public static final String EXTRA_SPINNER_DIFFICULTY_VALUES = "spinner_difficulty_value";

    private int sliderAmountSelectedValue;
    private String spinnerCategorySelectedValue;
    private String spinnerDifficultySelectedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        getValues();
        getQuestion();

    }

    private void getValues() {
        if (getIntent() != null) {
            sliderAmountSelectedValue = getIntent().getIntExtra(EXTRA_SLIDER_VALUES, 1);
            spinnerCategorySelectedValue = getIntent().getStringExtra(EXTRA_SPINNER_CATEGORY_VALUES);
            spinnerDifficultySelectedValue = getIntent().getStringExtra(EXTRA_SPINNER_DIFFICULTY_VALUES);

            if (spinnerCategorySelectedValue.equals("Any Category")) {
                spinnerCategorySelectedValue = null;
            }
            if (spinnerDifficultySelectedValue.equals("Any Difficulty")) {
                spinnerDifficultySelectedValue = null;
            }
        }
    }

    private void getQuestion() {
        QuizApp.quizAPIClient.getQuestions(
                sliderAmountSelectedValue,
                spinnerCategorySelectedValue,
                spinnerDifficultySelectedValue,
                new IQuizAPIClient.QuestionsCallback() {
                    @Override
                    public void onSuccess(List<Question> questions) {

                    }

                    @Override
                    public void onFailure(Exception exception) {

                    }
                });
    }
}
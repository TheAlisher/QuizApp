package com.example.quiz.ui.result;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quiz.QuizApp;
import com.example.quiz.R;
import com.example.quiz.models.QuizResult;

import java.util.Calendar;
import java.util.Date;

public class ResultActivity extends AppCompatActivity {

    private TextView textCategory;
    private TextView textDifficulty;
    private TextView textCorrectAnswers;
    private TextView textResult;
    private Button buttonFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initializationViews();
        setValues();
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickFinish();
            }
        });
    }

    private void initializationViews() {
        textCategory = findViewById(R.id.text_result_category);
        textDifficulty = findViewById(R.id.text_result_difficulty);
        textCorrectAnswers = findViewById(R.id.text_result_correct_answers);
        textResult = findViewById(R.id.text_result);
        buttonFinish = findViewById(R.id.materialbutton_result_finish);
    }

    private void setValues() {
        textCategory.setText(QuizApp.preferences.questionsCategory());
        textDifficulty.setText(QuizApp.preferences.questionsDifficulty());
        textCorrectAnswers.setText(
                QuizApp.preferences.questionCorrectAnswers() + "/" + QuizApp.preferences.questionsSize()
        );
        int percent = (int) (
                (float) QuizApp.preferences.questionCorrectAnswers() / (float) QuizApp.preferences.questionsSize() * 100
        );
        textResult.setText(percent + "%");
    }

    private void clickFinish() {
        Date currentTime = Calendar.getInstance().getTime();
        QuizResult quizResult = new QuizResult(
                QuizApp.preferences.questionsCategory(),
                QuizApp.preferences.questionsDifficulty(),
                QuizApp.preferences.questionCorrectAnswers(),
                QuizApp.preferences.questionsSize(),
                currentTime
        );
        QuizApp.quizDatabase.quizDao().insert(quizResult);
        finish();
    }
}
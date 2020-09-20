package com.example.quiz.ui.result;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quiz.QuizApp;
import com.example.quiz.R;
import com.example.quiz.models.QuizResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ResultActivity extends AppCompatActivity {

    private ImageView imageCheck;
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
        imageCheck = findViewById(R.id.image_result_check);
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

        if (percent < 50) {
            imageCheck.setVisibility(View.GONE);
        } else {
            imageCheck.setVisibility(View.VISIBLE);
        }
    }

    private void clickFinish() {
        Date timeIsNow = new Date();
        QuizResult quizResult = new QuizResult(
                QuizApp.preferences.questionsCategory(),
                QuizApp.preferences.questionsDifficulty(),
                QuizApp.preferences.questionCorrectAnswers(),
                QuizApp.preferences.questionsSize(),
                timeIsNow
        );
        QuizApp.quizDatabase.quizDao().insert(quizResult);
        finish();
    }
}
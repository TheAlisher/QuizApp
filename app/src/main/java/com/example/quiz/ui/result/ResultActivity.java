package com.example.quiz.ui.result;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quiz.R;

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

    private void clickFinish() {
        finish();
    }
}
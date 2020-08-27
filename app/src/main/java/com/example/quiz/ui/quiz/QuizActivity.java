package com.example.quiz.ui.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.quiz.QuizApp;
import com.example.quiz.R;
import com.example.quiz.data.remote.IQuizAPIClient;
import com.example.quiz.models.Question;
import com.example.quiz.ui.quiz.recycler.QuestionAdapter;
import com.example.quiz.ui.quiz.recycler.QuestionViewHolder;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements QuestionViewHolder.Listener {

    public static final String EXTRA_SLIDER_VALUES = "slider_value";
    public static final String EXTRA_SPINNER_CATEGORY_VALUES = "spinner_category_value";
    public static final String EXTRA_SPINNER_DIFFICULTY_VALUES = "spinner_difficulty_value";

    public static void start(Context context, int amount, String category, String difficulty) {
        Intent intent = new Intent(context, QuizActivity.class);
        intent.putExtra(EXTRA_SLIDER_VALUES, amount);
        intent.putExtra(EXTRA_SPINNER_CATEGORY_VALUES, category);
        intent.putExtra(EXTRA_SPINNER_DIFFICULTY_VALUES, difficulty);
        context.startActivity(intent);
    }

    private QuizViewModel mViewModel;

    private int sliderAmountSelectedValue;
    private String spinnerCategorySelectedValue;
    private String spinnerDifficultySelectedValue;

    private RecyclerView recyclerView;
    private QuestionAdapter adapter;
    private ArrayList<Question> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        getValues();
        mViewModel.init(sliderAmountSelectedValue, spinnerCategorySelectedValue, spinnerDifficultySelectedValue);

        getQuestion();
        createRecycler();
    }

    private void createRecycler() {
        recyclerView = findViewById(R.id.recycler_quiz);
        recyclerView.setLayoutManager(new LinearLayoutManager
                (this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new QuestionAdapter(this);
        recyclerView.setAdapter(adapter);

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        answerClick();
    }

    @Override
    public void onAnswerClick(int position, int selectAnswerPosition) {
        mViewModel.onAnswerClick(position, selectAnswerPosition);
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

    private void answerClick() {
        mViewModel.currentQuestionPosition.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                recyclerView.smoothScrollToPosition(integer);
            }
        });
    }

    private void getQuestion() {
        mViewModel.init(sliderAmountSelectedValue, spinnerCategorySelectedValue, spinnerDifficultySelectedValue);
        mViewModel.questions.observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                adapter.setQuestions(questions);
            }
        });
    }
}
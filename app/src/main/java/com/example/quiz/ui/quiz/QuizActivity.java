package com.example.quiz.ui.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.quiz.QuizApp;
import com.example.quiz.R;
import com.example.quiz.models.Question;
import com.example.quiz.ui.quiz.recycler.QuestionAdapter;
import com.example.quiz.ui.quiz.recycler.QuestionViewHolder;
import com.example.quiz.ui.result.ResultActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuizActivity extends AppCompatActivity implements QuestionViewHolder.Listener {

    public static final String EXTRA_SLIDER_VALUES = "slider_value";
    public static final String EXTRA_SPINNER_CATEGORY_VALUES = "spinner_category_value";
    public static final String EXTRA_SPINNER_DIFFICULTY_VALUES = "spinner_difficulty_value";

    public static void start(Context context, int amount, int category, String difficulty) {
        Intent intent = new Intent(context, QuizActivity.class);
        intent.putExtra(EXTRA_SLIDER_VALUES, amount);
        intent.putExtra(EXTRA_SPINNER_CATEGORY_VALUES, category);
        intent.putExtra(EXTRA_SPINNER_DIFFICULTY_VALUES, difficulty);
        context.startActivity(intent);
    }

    private QuizViewModel mViewModel;

    private int sliderAmountSelectedValue;
    private int spinnerCategorySelectedValue;
    private String spinnerDifficultySelectedValue;

    private ProgressBar progressBarStages;
    private Button buttonSkip;
    private ProgressBar progressBarLoading;
    private TextView textPassedStages;
    private TextView textQuestions;
    private Toolbar toolbar;

    private RecyclerView recyclerView;
    private QuestionAdapter adapter;
    private ArrayList<Question> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);

        setToolbar();
        initializationViews();
        getValues();
        observeFinishQuiz();
        getQuestion();
        createRecycler();
        answerClick();
        startResult();
        buttonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.onSkipClick(list.size());
            }
        });
    }

    private void observeFinishQuiz() {
        mViewModel.finishQuiz.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    finish();
                }
            }
        });
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar_quiz);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mViewModel.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializationViews() {
        progressBarStages = findViewById(R.id.progress_quiz_stages);
        buttonSkip = findViewById(R.id.button_quiz_skip);
        progressBarLoading = findViewById(R.id.progressBar_loading);
        textPassedStages = findViewById(R.id.text_quiz_passed_stages);
        textQuestions = findViewById(R.id.text_quiz_questions);
    }

    private void createRecycler() {
        recyclerView = findViewById(R.id.recycler_quiz);
        recyclerView.setLayoutManager(new LinearLayoutManager
                (this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new QuestionAdapter(list, this);
        recyclerView.setAdapter(adapter);

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onAnswerClick(int position, int selectAnswerPosition) {
        mViewModel.onAnswerClick(position, selectAnswerPosition);
    }

    private void getValues() {
        if (getIntent() != null) {
            sliderAmountSelectedValue = getIntent().getIntExtra(EXTRA_SLIDER_VALUES, 1);
            spinnerCategorySelectedValue = getIntent().getIntExtra(EXTRA_SPINNER_CATEGORY_VALUES, 0);
            spinnerDifficultySelectedValue = getIntent().getStringExtra(EXTRA_SPINNER_DIFFICULTY_VALUES);

            if (spinnerCategorySelectedValue == 8) {
                spinnerCategorySelectedValue = 0;
            }
            if (spinnerDifficultySelectedValue.equals("Any Difficulty")) {
                spinnerDifficultySelectedValue = null;
            }
        }
    }

    private void getQuestion() {
        loading();
        mViewModel.init(sliderAmountSelectedValue, spinnerCategorySelectedValue, spinnerDifficultySelectedValue);
        mViewModel.questions.observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                adapter.setQuestions(questions);
                textQuestions.setText("/" + questions.size());

                Objects.requireNonNull(getSupportActionBar()).setTitle(questions.get(0).getCategory());
                QuizApp.preferences.setQuestionCategory(questions.get(0).getCategory());
                QuizApp.preferences.setQuestionSize(questions.size());
            }
        });
    }

    private void loading() {
        mViewModel.isLoading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    toolbar.setVisibility(View.GONE);
                    progressBarStages.setVisibility(View.GONE);
                    textPassedStages.setVisibility(View.GONE);
                    textQuestions.setVisibility(View.GONE);
                    buttonSkip.setVisibility(View.GONE);
                    progressBarLoading.setVisibility(View.VISIBLE);
                } else {
                    toolbar.setVisibility(View.VISIBLE);
                    progressBarStages.setVisibility(View.VISIBLE);
                    textPassedStages.setVisibility(View.VISIBLE);
                    textQuestions.setVisibility(View.VISIBLE);
                    buttonSkip.setVisibility(View.VISIBLE);
                    progressBarLoading.setVisibility(View.GONE);
                }
            }
        });
    }

    private void answerClick() {
        mViewModel.currentQuestionPosition.observe(QuizActivity.this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                recyclerView.smoothScrollToPosition(integer);
                integer += 1;
                textPassedStages.setText(integer.toString());
                int progress = (int) ((float) integer / (float) QuizApp.preferences.questionsSize() * 50);
                progressBarStages.setProgress(progress);
            }
        });
    }

    private void startResult() {
        mViewModel.startResult.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                startActivity(new Intent(QuizActivity.this, ResultActivity.class));
                finish();
            }
        });
    }
}
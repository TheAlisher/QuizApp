package com.example.quiz.ui.quiz.recycler;

import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quiz.QuizApp;
import com.example.quiz.R;
import com.example.quiz.models.Question;
import com.google.android.material.button.MaterialButton;

import java.util.Timer;
import java.util.TimerTask;

public class QuestionViewHolder extends RecyclerView.ViewHolder {

    private TextView textQuestion;
    private LinearLayout linearMultiple;
    private MaterialButton buttonMultiplyAnswer1;
    private MaterialButton buttonMultiplyAnswer2;
    private MaterialButton buttonMultiplyAnswer3;
    private MaterialButton buttonMultiplyAnswer4;
    private LinearLayout linearBoolean;
    private MaterialButton buttonBooleanAnswer1;
    private MaterialButton buttonBooleanAnswer2;

    private Listener listener;
    private Question question;

    public QuestionViewHolder(@NonNull View itemView, Listener listener) {
        super(itemView);
        this.listener = listener;
        initializationViews(itemView);
        buttonMultiplyAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerClick(0);
            }
        });
        buttonMultiplyAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerClick(1);
            }
        });
        buttonMultiplyAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerClick(2);
            }
        });
        buttonMultiplyAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerClick(3);
            }
        });
        buttonBooleanAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerClick(0);
            }
        });
        buttonBooleanAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerClick(1);
            }
        });
    }

    private void initializationViews(View itemView) {
        textQuestion = itemView.findViewById(R.id.text_question_multiply);
        linearMultiple = itemView.findViewById(R.id.linear_question_multiple);
        buttonMultiplyAnswer1 = itemView.findViewById(R.id.button_multiply_1);
        buttonMultiplyAnswer2 = itemView.findViewById(R.id.button_multiply_2);
        buttonMultiplyAnswer3 = itemView.findViewById(R.id.button_multiply_3);
        buttonMultiplyAnswer4 = itemView.findViewById(R.id.button_multiply_4);
        linearBoolean = itemView.findViewById(R.id.linear_question_boolean);
        buttonBooleanAnswer1 = itemView.findViewById(R.id.button_boolean_1);
        buttonBooleanAnswer2 = itemView.findViewById(R.id.button_boolean_2);
    }

    public void onBind(Question question) {
        textQuestion.setText(Html.fromHtml(question.getQuestion()));
        if (question.getType().equals("multiple")) {
            multipleVisibility();
            buttonMultiplyAnswer1.setText(Html.fromHtml(question.getAnswers().get(0)));
            buttonMultiplyAnswer2.setText(Html.fromHtml(question.getAnswers().get(1)));
            buttonMultiplyAnswer3.setText(Html.fromHtml(question.getAnswers().get(2)));
            buttonMultiplyAnswer4.setText(Html.fromHtml(question.getAnswers().get(3)));
        } else if (question.getType().equals("boolean")) {
            booleanVisibility();
            buttonBooleanAnswer1.setText(Html.fromHtml(question.getAnswers().get(0)));
            buttonBooleanAnswer2.setText(Html.fromHtml(question.getAnswers().get(1)));
        }
        this.question = question;
    }

    private void multipleVisibility() {
        linearMultiple.setVisibility(View.VISIBLE);
        linearBoolean.setVisibility(View.GONE);
    }

    private void booleanVisibility() {
        linearMultiple.setVisibility(View.GONE);
        linearBoolean.setVisibility(View.VISIBLE);
    }

    private void answerClick(int selectAnswerPosition) {
        QuizApp.preferences.setQuestionCategory(question.getCategory());
        setScheduleColor(selectAnswerPosition);
        Timer timer = new Timer();
        if (question.getAnswers().get(selectAnswerPosition).equals(question.getCorrectAnswers())) {
            saveCorrectAnswers();
            setRightColor(timer, selectAnswerPosition);
        } else {
            setWrongColor(timer, selectAnswerPosition);
        }
        startNextItem(selectAnswerPosition);
        /*setEnabledFalse();
        setDefaultColor();*/
    }

    private void setButtonColors(MaterialButton materialButton, int strokeColor, int backgroundColor, int textColor) {
        materialButton.setBackgroundTintList(ContextCompat.getColorStateList(materialButton.getContext(), backgroundColor));
        materialButton.setStrokeColor(ContextCompat.getColorStateList(materialButton.getContext(), strokeColor));
        materialButton.setTextColor(materialButton.getContext().getResources().getColor(textColor));
    }

    private void setScheduleColor(int selectAnswerPosition) {
        switch (selectAnswerPosition) {
            case 0:
                setButtonColors(buttonMultiplyAnswer1, R.color.Schedule, R.color.Schedule, R.color.White);
                setButtonColors(buttonBooleanAnswer1, R.color.Schedule, R.color.Schedule, R.color.White);
                break;
            case 1:
                setButtonColors(buttonMultiplyAnswer2, R.color.Schedule, R.color.Schedule, R.color.White);
                setButtonColors(buttonBooleanAnswer2, R.color.Schedule, R.color.Schedule, R.color.White);
                break;
            case 2:
                setButtonColors(buttonMultiplyAnswer3, R.color.Schedule, R.color.Schedule, R.color.White);
                break;
            case 3:
                setButtonColors(buttonMultiplyAnswer4, R.color.Schedule, R.color.Schedule, R.color.White);
                break;
        }
    }

    private void setRightColor(Timer timer, int selectAnswerPosition) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                switch (selectAnswerPosition) {
                    case 0:
                        setButtonColors(buttonMultiplyAnswer1, R.color.Right, R.color.Right, R.color.White);
                        setButtonColors(buttonBooleanAnswer1, R.color.Right, R.color.Right, R.color.White);
                        break;
                    case 1:
                        setButtonColors(buttonMultiplyAnswer2, R.color.Right, R.color.Right, R.color.White);
                        setButtonColors(buttonBooleanAnswer2, R.color.Right, R.color.Right, R.color.White);
                        break;
                    case 2:
                        setButtonColors(buttonMultiplyAnswer3, R.color.Right, R.color.Right, R.color.White);
                        break;
                    case 3:
                        setButtonColors(buttonMultiplyAnswer4, R.color.Right, R.color.Right, R.color.White);
                        break;
                }
            }
        }, 500);
    }

    private void setWrongColor(Timer timer, int selectAnswerPosition) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                switch (selectAnswerPosition) {
                    case 0:
                        setButtonColors(buttonMultiplyAnswer1, R.color.Wrong, R.color.Wrong, R.color.White);
                        setButtonColors(buttonBooleanAnswer1, R.color.Wrong, R.color.Wrong, R.color.White);
                        break;
                    case 1:
                        setButtonColors(buttonMultiplyAnswer2, R.color.Wrong, R.color.Wrong, R.color.White);
                        setButtonColors(buttonBooleanAnswer2, R.color.Wrong, R.color.Wrong, R.color.White);
                        break;
                    case 2:
                        setButtonColors(buttonMultiplyAnswer3, R.color.Wrong, R.color.Wrong, R.color.White);
                        break;
                    case 3:
                        setButtonColors(buttonMultiplyAnswer4, R.color.Wrong, R.color.Wrong, R.color.White);
                        break;
                }
            }
        }, 500);
    }

    private void startNextItem(int selectAnswerPosition) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onAnswerClick(getAdapterPosition(), selectAnswerPosition);
            }
        }, 1000);
    }

    private void setEnabledFalse() {
        buttonMultiplyAnswer1.setEnabled(false);
        buttonMultiplyAnswer2.setEnabled(false);
        buttonMultiplyAnswer3.setEnabled(false);
        buttonMultiplyAnswer4.setEnabled(false);
        buttonBooleanAnswer1.setEnabled(false);
        buttonBooleanAnswer2.setEnabled(false);
    }

    private void setDefaultColor() {
        setButtonColors(buttonMultiplyAnswer1, R.color.Schedule, R.color.White, R.color.Schedule);
        setButtonColors(buttonMultiplyAnswer2, R.color.Schedule, R.color.White, R.color.Schedule);
        setButtonColors(buttonMultiplyAnswer3, R.color.Schedule, R.color.White, R.color.Schedule);
        setButtonColors(buttonMultiplyAnswer4, R.color.Schedule, R.color.White, R.color.Schedule);
        setButtonColors(buttonBooleanAnswer1, R.color.Schedule, R.color.White, R.color.Schedule);
        setButtonColors(buttonBooleanAnswer2, R.color.Schedule, R.color.White, R.color.Schedule);
    }

    private void saveCorrectAnswers() {
        int correctAnswers = QuizApp.preferences.questionCorrectAnswers() + 1;
        QuizApp.preferences.setQuestionCorrectAnswers(correctAnswers);
    }

    public interface Listener {
        void onAnswerClick(int position, int selectAnswerPosition);
    }
}

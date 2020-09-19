package com.example.quiz.ui.quiz.recycler;

import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quiz.R;
import com.example.quiz.models.Question;
import com.google.android.material.button.MaterialButton;

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

    public QuestionViewHolder(@NonNull View itemView, Listener listener) {
        super(itemView);
        this.listener = listener;
        initializationViews(itemView);
        buttonMultiplyAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerClick(1);
            }
        });
        buttonMultiplyAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerClick(2);
            }
        });
        buttonMultiplyAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerClick(3);
            }
        });
        buttonMultiplyAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerClick(4);
            }
        });
        buttonBooleanAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerClick(1);
            }
        });
        buttonBooleanAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerClick(2);
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
        listener.onAnswerClick(getAdapterPosition(), selectAnswerPosition);
    }

    public interface Listener {
        void onAnswerClick(int position, int selectAnswerPosition);
    }
}

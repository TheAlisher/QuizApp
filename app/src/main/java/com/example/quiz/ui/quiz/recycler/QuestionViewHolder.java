package com.example.quiz.ui.quiz.recycler;

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
        textQuestion.setText(question.getQuestion());
        if (question.getType().equals("multiple")) {
            multipleVisibility();
            buttonMultiplyAnswer1.setText(question.getAnswers().get(0));
            buttonMultiplyAnswer2.setText(question.getAnswers().get(1));
            buttonMultiplyAnswer3.setText(question.getAnswers().get(2));
            buttonMultiplyAnswer4.setText(question.getAnswers().get(3));
        } else if (question.getType().equals("boolean")) {
            booleanVisibility();
            buttonBooleanAnswer1.setText(question.getAnswers().get(0));
            buttonBooleanAnswer2.setText(question.getAnswers().get(1));
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

    public interface Listener {
        void onAnswerClick(int position, int selectAnswerPosition);
    }
}

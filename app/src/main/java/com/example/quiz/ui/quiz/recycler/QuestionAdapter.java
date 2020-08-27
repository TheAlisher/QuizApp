package com.example.quiz.ui.quiz.recycler;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quiz.R;
import com.example.quiz.models.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Question> list;
    private QuestionViewHolder.Listener listener;

    public QuestionAdapter(QuestionViewHolder.Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuestionViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_question_item, parent, false),
                listener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof QuestionViewHolder) {
            ((QuestionViewHolder) holder).onBind(list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setQuestions(List<Question> questions) {
        list.clear();
        list.addAll(questions);
        notifyDataSetChanged();
    }
}

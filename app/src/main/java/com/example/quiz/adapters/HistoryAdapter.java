package com.example.quiz.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quiz.R;
import com.example.quiz.interfaces.OnItemClickListener;
import com.example.quiz.models.QuizResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private ArrayList<QuizResult> list;
    private OnItemClickListener onItemClickListener;

    public HistoryAdapter(ArrayList<QuizResult> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_history_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {

        private CardView cardHistory;
        private TextView textCategory;
        private TextView textCorrectAnswers;
        private TextView textDifficulty;
        private TextView textTime;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            initializationViews(itemView);
            cardHistory.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemClickListener.onHistoryItemLongClick(getAdapterPosition());
                    return true;
                }
            });
        }

        private void initializationViews(View itemView) {
            cardHistory = itemView.findViewById(R.id.card_history);
            textCategory = itemView.findViewById(R.id.text_history_category);
            textCorrectAnswers = itemView.findViewById(R.id.text_history_correct_answers);
            textDifficulty = itemView.findViewById(R.id.text_history_difficulty);
            textTime = itemView.findViewById(R.id.text_history_time);
        }

        public void onBind(QuizResult quizResult) {
            textCategory.setText(quizResult.getCategory());
            textCorrectAnswers.setText(String.valueOf(quizResult.getCorrectAnswers()) + "/" + quizResult.getQuestions());
            textDifficulty.setText(quizResult.getDifficulty());
            SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy hh:mm", Locale.ENGLISH);
            textTime.setText(formatter.format(quizResult.getCreatedAt()));
        }
    }
}

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
import com.example.quiz.models.History;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private ArrayList<History> list;
    private OnItemClickListener onItemClickListener;

    public HistoryAdapter(ArrayList<History> list) {
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

        public void onBind(History result) {
            textCategory.setText(result.getCategory());
            textCorrectAnswers.setText(result.getCorrectAnswers());
            textDifficulty.setText(result.getDifficulty());
            textTime.setText(result.getTime());
        }
    }
}

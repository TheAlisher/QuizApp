package com.example.quiz.adapters;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quiz.QuizApp;
import com.example.quiz.R;
import com.example.quiz.interfaces.OnItemClickListener;
import com.example.quiz.models.QuizResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private ArrayList<QuizResult> list;

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

    public class HistoryViewHolder extends RecyclerView.ViewHolder {

        private CardView cardHistory;
        private ImageView imageMore;
        private TextView textCategory;
        private TextView textCorrectAnswers;
        private TextView textDifficulty;
        private TextView textTime;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            initializationViews(itemView);
            imageMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            if (menuItem.getItemId() == R.id.popup_delete) {
                                clickPopupDelete(view);
                                return true;
                            }
                            return false;
                        }
                    });
                    popupMenu.inflate(R.menu.popup_menu);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        popupMenu.setForceShowIcon(true);
                    }
                    popupMenu.show();
                }
            });
        }

        private void initializationViews(View itemView) {
            cardHistory = itemView.findViewById(R.id.card_history);
            imageMore = itemView.findViewById(R.id.image_history_more);
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

        private void clickPopupDelete(View view) {
            QuizApp.quizDatabase.quizDao().delete(list.get(getAdapterPosition()));
        }
    }
}

package com.example.quiz.ui.settings;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiz.QuizApp;
import com.example.quiz.R;

public class SettingsFragment extends Fragment {

    private TextView textShareThisApp;
    private TextView textRateUs;
    private TextView textLeaveFeedback;
    private TextView textClearHistory;

    private SettingsViewModel mViewModel;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializationViews(view);
        textClearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickClearHistory();
            }
        });
    }

    private void initializationViews(View view) {
        textShareThisApp = view.findViewById(R.id.text_settings_share_this_app);
        textRateUs = view.findViewById(R.id.text_settings_rate_us);
        textLeaveFeedback = view.findViewById(R.id.text_settings_leave_feedback);
        textClearHistory = view.findViewById(R.id.text_settings_clear_history);
    }

    private void clickClearHistory() {
        QuizApp.quizDatabase.quizDao().deleteAll();
        Toast.makeText(getContext(), "History cleared", Toast.LENGTH_SHORT).show();
    }
}
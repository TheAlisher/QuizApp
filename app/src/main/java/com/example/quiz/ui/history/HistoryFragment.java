package com.example.quiz.ui.history;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quiz.QuizApp;
import com.example.quiz.R;
import com.example.quiz.adapters.HistoryAdapter;
import com.example.quiz.interfaces.OnItemClickListener;
import com.example.quiz.models.QuizResult;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    private HistoryAdapter adapter;
    private ArrayList<QuizResult> list = new ArrayList<>();

    private HistoryViewModel mViewModel;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.history_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createHistoryRecycler(view);
    }

    private void createHistoryRecycler(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HistoryAdapter(list);
        recyclerView.setAdapter(adapter);
        loadData();
    }

    private void loadData() {
        QuizApp.quizDatabase.quizDao().getAll().observe(getViewLifecycleOwner(), new Observer<List<QuizResult>>() {
            @Override
            public void onChanged(List<QuizResult> quizResults) {
                list.clear();
                list.addAll(quizResults);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
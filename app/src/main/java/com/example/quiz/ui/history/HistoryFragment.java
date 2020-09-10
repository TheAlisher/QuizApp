package com.example.quiz.ui.history;

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
import com.example.quiz.models.History;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private HistoryAdapter historyAdapter;
    private ArrayList<History> list = new ArrayList<>();

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
        historyAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onHistoryItemLongClick(int position) {
                longClickOnItem(position);
            }
        });
    }

    private void createHistoryRecycler(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        historyAdapter = new HistoryAdapter(list);
        recyclerView.setAdapter(historyAdapter);
    }

    private void longClickOnItem(int position) {
        QuizApp.repository.delete(position);
    }
}
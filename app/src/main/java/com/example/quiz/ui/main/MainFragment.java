package com.example.quiz.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quiz.R;
import com.google.android.material.slider.Slider;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;

    private Slider slider;
    private Spinner spinnerCategory;
    private Spinner spinnerDifficulty;
    private Button buttonStart;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.message.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializationViews(view);
        createSpinner(spinnerCategory, R.array.spinner_category);
        createSpinner(spinnerDifficulty, R.array.spinner_difficulty);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainStartClick();
            }
        });
    }

    private void initializationViews(View view) {
        slider = view.findViewById(R.id.slider_main);
        spinnerCategory = view.findViewById(R.id.spinner_main_category);
        spinnerDifficulty = view.findViewById(R.id.spinner_main_difficulty);
        buttonStart = view.findViewById(R.id.button_main_start);
    }

    private void createSpinner(Spinner spinner, int array) {
        String[] dropdownCategory = getResources().getStringArray(array);
        ArrayAdapter<String> adapter = new
                ArrayAdapter<>(requireContext(), R.layout.custom_spinner_item, dropdownCategory);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void mainStartClick() {

    }
}
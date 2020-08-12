package com.example.quiz.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.quiz.R;
import com.example.quiz.adapters.MainPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        initializationViews();
        createMainBottomNavigationWithViewPager();
    }

    private void initializationViews() {
        viewPager = findViewById(R.id.viewPager_main);
        bottomNavigationView = findViewById(R.id.bottomNavigation_main);
    }

    private void createMainBottomNavigationWithViewPager() {
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(2);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_explore:
                        viewPager.setCurrentItem(0, false);
                        break;
                    case R.id.navigation_map:
                        viewPager.setCurrentItem(1, false);
                        break;
                    case R.id.navigation_settings:
                        viewPager.setCurrentItem(2, false);
                        break;
                }
                return true;
            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0.0f);
            getSupportActionBar().setTitle(R.string.textTitle_main_quiz);
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (getSupportActionBar() != null) {
                    switch (position) {
                        case 0:
                            getSupportActionBar().setTitle(R.string.textTitle_main_quiz);
                            break;
                        case 1:
                            getSupportActionBar().setTitle(R.string.textTitle_main_history);
                            break;
                        case 2:
                            getSupportActionBar().setTitle(R.string.textTitle_main_settings);
                            break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
package com.example.quiz;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.quiz.data.AppPreferences;
import com.example.quiz.data.QuizRepository;
import com.example.quiz.data.remote.IHistoryStorage;
import com.example.quiz.data.remote.IQuizAPIClient;
import com.example.quiz.data.remote.QuizAPIClient;
import com.example.quiz.db.QuizDao;
import com.example.quiz.db.QuizDatabase;
import com.example.quiz.ui.history.HistoryStorage;

public class QuizApp extends Application {

    public static IQuizAPIClient quizAPIClient;
    private static IHistoryStorage historyStorage;
    public static QuizDatabase quizDatabase;

    public static QuizRepository repository;
    public static AppPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();

        quizAPIClient = new QuizAPIClient();
        historyStorage = new HistoryStorage();
        quizDatabase = Room.databaseBuilder(
                this,
                QuizDatabase.class,
                "quiz.database"
        )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        repository = new QuizRepository(quizAPIClient, historyStorage, quizDatabase.quizDao());
        preferences = new AppPreferences(this);

    }
}

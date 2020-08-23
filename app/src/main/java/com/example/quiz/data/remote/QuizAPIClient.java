package com.example.quiz.data.remote;

import android.util.Log;

import com.example.quiz.models.QuizResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QuizAPIClient implements IQuizAPIClient {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    QuizAPI client = retrofit.create(QuizAPI.class);

    @Override
    public void getQuestions(
            int amount,
            String category,
            String difficulty,
            QuestionsCallback callback
    ) {
        Call<QuizResponse> call = client.getQuestion(
                amount,
                category,
                difficulty
        );

        Log.d("anime", "URL - " + call.request().url());

        call.enqueue(new Callback<QuizResponse>() {
            @Override
            public void onResponse(Call<QuizResponse> call, Response<QuizResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        callback.onSuccess(response.body().getResults());
                    } else {
                        callback.onFailure(new Exception("Response body is empty " + response.code()));
                    }
                } else {
                    callback.onFailure(new Exception("Response is empty " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<QuizResponse> call, Throwable t) {
                callback.onFailure(new Exception(t));
            }
        });
    }

    private interface QuizAPI {
        @GET("api.php")
        Call<QuizResponse> getQuestion(
                @Query("amount") int amount,
                @Query("category") String category,
                @Query("difficulty") String difficulty
        );
    }
}

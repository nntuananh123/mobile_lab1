package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText inputText;
    private ImageView sentimentIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputText);
        Button submitButton = findViewById(R.id.submitButton);
        sentimentIcon = findViewById(R.id.sentimentIcon);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = inputText.getText().toString().trim();
                if (!text.isEmpty()) {
                    analyzeSentiment(text);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a sentence", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void analyzeSentiment(String text) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://generativelanguage.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        SentimentRequest request = new SentimentRequest(text);

        Call<SentimentResponse> call = apiService.analyzeSentiment(request);
        call.enqueue(new Callback<SentimentResponse>() {
            @Override
            public void onResponse(@NonNull Call<SentimentResponse> call, @NonNull Response<SentimentResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String sentiment = response.body().getSentiment().toLowerCase();
                    updateSentimentIcon(sentiment);
                } else {
                    try {
                        String errorBody = response.errorBody() != null ? response.errorBody().string() : "No error body";
                        Toast.makeText(MainActivity.this, "Failed: " + response.code() + " - " + errorBody, Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Failed: " + response.code() + " - Error parsing response", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<SentimentResponse> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateSentimentIcon(String sentiment) {
        switch (sentiment) {
            case "positive":
                sentimentIcon.setImageResource(R.drawable.happy);
                break;
            case "negative":
                sentimentIcon.setImageResource(R.drawable.sad);
                break;
            case "neutral":
                sentimentIcon.setImageResource(R.drawable.neutral);
                break;
            default:
                sentimentIcon.setImageResource(android.R.drawable.ic_dialog_alert);
                Toast.makeText(this, "Unknown sentiment: " + sentiment, Toast.LENGTH_SHORT).show();
        }
    }
}
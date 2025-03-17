package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("v1beta/models/gemini-2.0-flash:generateContent?key=AIzaSyBvGuBk7sOnvRsAFrvBhWq0i0Y0J_QAWhg") // Cập nhật endpoint
    Call<SentimentResponse> analyzeSentiment(@Body SentimentRequest request);
}
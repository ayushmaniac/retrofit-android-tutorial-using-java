package com.gradlic.student.project.retrofitdemoapp;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitService {

    private static final String BASE_URL = "https://shrouded-beyond-67487.herokuapp.com/";
    private static RetrofitService mInstance;

    private Retrofit mRetrofit;

    private RetrofitService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public static RetrofitService getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitService();
        }
        return mInstance;
    }
    public RetrofitApi getJSONApi() {
        return mRetrofit.create(RetrofitApi.class);
    }
}

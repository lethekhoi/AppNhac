package com.example.appnhac.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class APIRetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String url) {
        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(1000000000 , TimeUnit.MILLISECONDS)
                .writeTimeout(1000000000 , TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }

}
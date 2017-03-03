package com.android.redditreader.common.application;

import android.app.Application;

import com.android.redditreader.api.SubRedditDeserializer;
import com.android.redditreader.dataaccess.entities.SubRedditResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RedditReaderApplication extends Application {

    private static final String BASE_URL = "https://www.reddit.com/";
    private static Retrofit mRetrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(SubRedditResponse.class, new SubRedditDeserializer())
                .create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        mRetrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build();
    }

    public static Retrofit getRetrofit() {
        return mRetrofit;
    }
}

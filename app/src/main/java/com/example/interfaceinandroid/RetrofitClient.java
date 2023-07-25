package com.example.interfaceinandroid;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient
{
public static String BASEURL="https://localserverpro.com/dev/laravel/super_travel/";
public static Retrofit retrofit;
public static Retrofit getRetrofit(){

    if (retrofit==null)
    {
      retrofit = new Retrofit.Builder().baseUrl(BASEURL)
              .addConverterFactory(GsonConverterFactory.create())
              .build();
    }


    return retrofit;
}
}

package com.example.finkacho.premote_android.helpers;

import com.example.finkacho.premote_android.data.Command;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface PremoteService {
    @GET("api/commands")
    Call<List<Command>> getCommands();


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}

package com.example.carre.alunodroid.restservice;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.carre.alunodroid.R;
import com.example.carre.alunodroid.rest.Aluno;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by carre on 07/01/2018.
 */

public class ControllerList {

    private static final String BASE_URL = "http://10.0.2.2:8080/alunoswb/";

    private static Retrofit retrofit;
    public static void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static AlunoService createService() {
        start();
        return retrofit.create(AlunoService.class);
    }


}

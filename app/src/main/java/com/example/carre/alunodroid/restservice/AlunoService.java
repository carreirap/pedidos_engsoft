package com.example.carre.alunodroid.restservice;

import com.example.carre.alunodroid.rest.Aluno;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by carre on 07/01/2018.
 */

public interface AlunoService {
    @GET("aluno")
    Call<List<Aluno>> listAllAlunos();

    @GET("aluno/{id}")
    Call<Aluno> getAluno(@Path("id") String id);

    @POST("aluno")
    Call<Aluno> saveAluno(@Body Aluno a);

    @PUT("aluno")
    Call<Aluno> updateAluno(@Body Aluno a);

    @DELETE("aluno/{id}")
    Call<Aluno> deleteAluno(@Path("id") String id);
}

package com.example.carre.alunodroid.restservice;

import com.example.carre.alunodroid.rest.Aluno;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by carre on 07/01/2018.
 */

public interface AlunoService {
    @GET("aluno")
    Call<List<Aluno>> listAllAlunos();
}

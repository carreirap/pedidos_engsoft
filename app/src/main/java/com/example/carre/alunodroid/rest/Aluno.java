package com.example.carre.alunodroid.rest;

/**
 * Created by carre on 07/01/2018.
 */

public class Aluno {

    private Integer id;
    private String CPF;
    private String nome;
    private String idade;
    private Endereco endereco;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCPF() {
        return CPF;
    }
    public void setCPF(String cPF) {
        CPF = cPF;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getIdade() {
        return idade;
    }
    public void setIdade(String idade) {
        this.idade = idade;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    @Override
    public String toString() {
        return "Aluno [Matricula=" + id + ", CPF=" + CPF + ", nome=" + nome +
                ", idade=" + idade + ", endereço=" + endereco.getLogradouro()
                + " " + endereco.getNumero() + "-" + endereco.getCidade() + "/" +
                endereco.getEstado() + "]";
    }

}

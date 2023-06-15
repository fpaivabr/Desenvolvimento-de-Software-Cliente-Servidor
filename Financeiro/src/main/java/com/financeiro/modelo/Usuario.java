package com.financeiro.modelo;

public class Usuario {
    private Integer id;
    private String login;
    private String senha;
    private String nome;

    public String getLogin() {
        return login;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }
    public void setLogin(String login) {
        this.login = login;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
package br.com.usermanage.usermanage.dto;

public class LoginRequest {
    private String nome;
    private String senha;

    // Getters e Setters
    public String getUsername() {
        return nome;
    }

    public void setUsername(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return senha;
    }

    public void setPassword(String senha) {
        this.senha = senha;
    }
}
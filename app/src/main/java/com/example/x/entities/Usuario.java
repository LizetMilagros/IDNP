package com.example.x.entities;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int id;
    private String username;
    private String email;
    private String password;
    private String repassword;

    public Usuario() {
    }

    public Usuario(String username, String email, String password, String repassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.repassword = repassword;
    }

    public Usuario(int id, String username, String email, String password, String repassword) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.repassword = repassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

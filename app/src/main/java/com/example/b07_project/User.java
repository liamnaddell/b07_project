package com.example.b07_project;

public class User {
    String username;
    String password;
    boolean is_admin;


    public User(String username, String password, boolean is_admin) {
        this.username = username;
        this.password = password;
        this.is_admin = is_admin;
    }
}

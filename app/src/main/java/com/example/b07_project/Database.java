package com.example.b07_project;

public interface Database {
    public boolean check_pw(String username, String password);
    public void add_user(String username, String password);
}

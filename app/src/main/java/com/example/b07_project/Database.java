package com.example.b07_project;

public interface Database {
    //checks that a user with username and password password exists in the database
    public boolean check_pw(String username, String password);
    //adds user with username, password into the database
    public void add_user(String username, String password);
}

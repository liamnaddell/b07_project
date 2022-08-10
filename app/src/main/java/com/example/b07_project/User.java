package com.example.b07_project;

import java.io.Serializable;

public class User implements Serializable {
    public String username;
    public String password;
    public boolean isAdmin;

    public User() {
        return;
    }

    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }
    @Override
    public String toString() {
        return "User [username="+username+", password="+password+",isAdmin="+isAdmin+"]";
    }
}

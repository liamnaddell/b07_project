package com.example.b07_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void login_handler(View view) {
        System.out.println("login handler");
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }
    public void venues_handler(View view) {
        System.out.println("venues handler");
    }
    public void event_handler(View view) {
        System.out.println("event handler");
    }
    public void user_handler(View view) {
        System.out.println("user handler");
    }

}
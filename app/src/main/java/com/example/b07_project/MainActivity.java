package com.example.b07_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Database db = DatabaseInstance.get_instance();
        db.add_user("admin","admin",true);
        db.add_user("user","user",false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void login_handler(View view) {
        System.out.println("login handler");
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
        finish();
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
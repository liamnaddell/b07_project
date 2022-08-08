package com.example.b07_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Database db = DatabaseInstance.get_instance();
        System.out.println("getting venus");
        System.out.println("venue:"+db.get_venue(5));
    }
    /**
    public void login_handler(View view) {
        System.out.println("login handler");
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
        finish();
    } */

    public void venues_handler(View view) {
        System.out.println("venues handler");
        Intent gotoVenuePage = new Intent(this, VenuePage.class);
        startActivity(gotoVenuePage);
    }
    public void event_handler(View view) {
        System.out.println("event handler");
        Intent gotoEventPage = new Intent(this, EventPage.class);
        startActivity(gotoEventPage);
    }

    public void user_handler(View view) {
        System.out.println("user handler");
        Intent intent = new Intent(this, UserPage.class);
        startActivity(intent);
    }

}

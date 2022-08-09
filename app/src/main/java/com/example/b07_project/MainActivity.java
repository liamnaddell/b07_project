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

//        Database db = DatabaseInstance.get_instance();
//        Event e = new Event(db.get_venue(0),24,"blobby deth day", "watch blobby die",0,
//                new EventTime(new TimeSlot(10),2,"Today")
//                );
//        System.out.println("added event:"+db.add_event(e));
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

package com.example.b07_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Database db = DatabaseInstance.get_instance();
        db.add_user("admin","admin",true);
        db.add_user("user","user",false);
        VenueType Basketball = VenueType.BASKETBALL_COURT;
        db.add_venue(Basketball,"UTSC","Nice");
        db.add_venue(Basketball,"Minh's House","Next to the Thamse");

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

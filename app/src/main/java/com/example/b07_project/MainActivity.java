package com.example.b07_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Database db = DatabaseInstance.get_instance();
        VenueType Basketball = VenueType.BASKETBALL_COURT;
        db.add_venue(Basketball,"UTSC","Nice");
        db.add_venue(Basketball,"Minh's House","Next to the Thamse");
        db.add_user("admin", "admin", true);
        db.add_user("user", "user", false);
        // hard code in list until server is ready
        int v1 = db.add_venue(VenueType.TENNIS_COURT,"toronto","toronto tennis court");
        int v2 = db.add_venue(VenueType.BASKETBALL_COURT,"1234 Military Trail","toronto basketball court");

        db.add_event(v1,"tennis", "play tennis",
                2, "5pm July 31", "10pm July 31");
        db.add_event(v1,"tennis alejandro", "play tennis w/ alejandro", 1, "5pm","6pm");
        db.add_event(v1, "random guy concert",
                "concert of a random guy", 3, "1am Aug 21",
                "10am Aug 21");
        db.add_event(v2, "weed smoking contest",
                "smoke week and have fun", 4, "1am Dec 22",
                "10am Dec 22");
        db.add_event(v2, "swallow kids event",
                "swallow kids and try not to choke", 5, "1am Sept 22",
                "10am Sept 22");

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

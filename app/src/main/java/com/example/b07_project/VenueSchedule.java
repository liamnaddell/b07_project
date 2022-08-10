package com.example.b07_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.b07_project.databinding.ActivityUserPageBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.Time;


public class VenueSchedule extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    VenueType v;
    String EventName, EventDes, date, venueId;
    int NumPeople, dur, StartTime;
    TimeSlot Start;

    EditText nameInput;
    EditText desInput;
    EditText NumPPl;
    EditText S_time;
    EditText duration;

    Spinner R_date;

    Button submitbutton, eliminate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_schedule);

        Database db = DatabaseInstance.get_instance();
        nameInput = (EditText) findViewById(R.id.nameInput);
        desInput = (EditText) findViewById(R.id.desInput);
        NumPPl = (EditText) findViewById(R.id.NumPPl);
        venueId = get_id();
        S_time = (EditText) findViewById(R.id.Start_Time);
        duration = (EditText) findViewById(R.id.Duration);
        R_date = (Spinner) findViewById(R.id.Week_Date);

        ArrayAdapter<String> dateAdapter = new ArrayAdapter<String>(VenueSchedule.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Date));
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        R_date.setAdapter(dateAdapter);

        submitbutton = (Button) findViewById(R.id.submit);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){

                EventName = nameInput.getText().toString();
                EventDes = desInput.getText().toString() ;
                NumPeople = Integer.valueOf(NumPPl.getText().toString());
                StartTime = Integer.valueOf(S_time.getText().toString());
                System.out.println("StartTIme:"+StartTime);
                int hour = StartTime / 100;
                System.out.println("Hour:"+hour);
                int minutes = StartTime % 100;
                System.out.println("Minute:"+minutes);
                if (!(minutes == 30 || minutes == 0) || hour > 24) {

                    Toast.makeText(VenueSchedule.this,"Time must be in format 1030 or 1000, in 30 minute intervals",
                            Toast.LENGTH_LONG).show();
                } else {
                    Start = new TimeSlot(hour*2+minutes/30);

                    dur = Integer.valueOf(duration.getText().toString());
                    if (dur % 30 != 0) {
                        Toast.makeText(VenueSchedule.this,"Duration must be in 30 minute intervals",
                                Toast.LENGTH_LONG).show();
                    } else {
                        dur = dur / 30;


                        Event e = new Event(db.get_venue(venueId), NumPeople, EventName, EventDes,
                                "", new EventTime(Start, dur, date));
                        db.add_event(e);

                        Toast.makeText(VenueSchedule.this, "Successfully Registered Event",
                                Toast.LENGTH_LONG).show();
                        goToVenue(v);
                    }
                }

            }
        });

        eliminate = (Button) findViewById(R.id.Del);

        if(db.is_admin(db.logged_in().username)){
            eliminate.setVisibility(View.VISIBLE);
        }else{
            eliminate.setVisibility(View.INVISIBLE);
        }

        eliminate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.delete_venue(venueId);
                Toast.makeText(VenueSchedule.this, "Deleted Venue: Please check the updated events.", Toast.LENGTH_SHORT).show();
                setContentView(R.layout.activity_main);
                BottomNavigationView bottomNavView;

                EventPage eventFragment = new EventPage();
                VenuePage venueFragment = new VenuePage();
                UserPage userFragment = new UserPage();
                bottomNavView = findViewById(R.id.bottomNavigationView);

                getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_container, eventFragment).commit();

                bottomNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item){
                        switch (item.getItemId()) {
                            case R.id.events:
                                getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_container, eventFragment).commit();
                                return true;

                            case R.id.venues:
                                getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_container, venueFragment).commit();
                                return true;

                            case R.id.my_account:
                                getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_container, userFragment).commit();
                                return true;
                        }
                        return false;
                    }
                });
            }
        });
    }

    private String get_id() {
        String result = null;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            result = bundle.getString("venueId");
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void goToVenue (View view){
        onBackPressed();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if( parent.getId() == R.id.Week_Date){
            date = parent.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        if(adapterView.getId() == R.id.Week_Date){
            Toast.makeText(VenueSchedule.this,"Please pick a day to register your event",
                    Toast.LENGTH_LONG).show();
        }
    }
}
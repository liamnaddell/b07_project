package com.example.b07_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Time;


public class VenueSchedule extends AppCompatActivity {
    VenueType v;
    String EventName, EventDes;
    int NumPeople, dur, id, StartTime;
    TimeSlot Start;

    EditText nameInput;
    EditText desInput;
    EditText NumPPl;
    EditText S_time;
    EditText duration;

    Button submitbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_schedule);

        nameInput = (EditText) findViewById(R.id.nameInput);
        desInput = (EditText) findViewById(R.id.desInput);
        NumPPl = (EditText) findViewById(R.id.NumPPl);
        id = get_id();
        S_time = (EditText) findViewById(R.id.Start_Time);
        duration = (EditText) findViewById(R.id.Duration);

        submitbutton = (Button) findViewById(R.id.submit);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){

                EventName = nameInput.getText().toString();
                EventDes = desInput.getText().toString() ;
                NumPeople = Integer.valueOf(NumPPl.getText().toString());
                StartTime = Integer.valueOf(S_time.getText().toString());
                TimeSlot Start = new TimeSlot(StartTime);
                dur = Integer.valueOf(duration.getText().toString())/30;
                Database db = DatabaseInstance.get_instance();
                db.add_event(id,EventName,EventDes,NumPeople,Start,dur);

                Toast.makeText(VenueSchedule.this,"Finish registering event",
                        Toast.LENGTH_LONG).show();
                goToVenue(v);

            }
        });
    }

    private int get_id() {
        Intent intent = new Intent();
        int intValue = intent.getIntExtra("eventid",0);
        return intValue;
    }

    public void goToVenue (View view){
        Intent intent = new Intent(this,VenuePage.class);
        startActivity(intent);

    }
}
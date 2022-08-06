package com.example.b07_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class VenueSchedule extends AppCompatActivity {
    VenueType v;
    String EventName, EventDes;
    int NumPeople;

    EditText nameInput;
    EditText desInput;
    EditText NumPPl;

    Button submitbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_schedule);

        nameInput = (EditText) findViewById(R.id.nameInput);
        desInput = (EditText) findViewById(R.id.desInput);

        NumPPl = (EditText) findViewById(R.id.NumPPl);

        submitbutton = (Button) findViewById(R.id.submit);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                EventName = nameInput.getText().toString();
                EventDes = desInput.getText().toString() ;
                NumPeople = Integer.valueOf(NumPPl.getText().toString());

            }
        });
    }

    public void goToVenue (View view){
        Intent intent = new Intent(this,VenuePage.class);
        startActivity(intent);

    }
}
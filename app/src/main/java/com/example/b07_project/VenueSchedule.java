package com.example.b07_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Locale;

public class VenueSchedule extends AppCompatActivity {
    VenueType v;
    String EventName,StartTime, EndTime, EventDes;
    int start_hrs, end_hrs, start_min, end_min, NumPeople;

    EditText nameInput;
    EditText desInput;
    EditText NumPPl;

    Button start_time_button, end_time_button, submitbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_schedule);

        nameInput = (EditText) findViewById(R.id.nameInput);
        desInput = (EditText) findViewById(R.id.desInput);

        NumPPl = (EditText) findViewById(R.id.NumPPl);
        start_time_button = findViewById(R.id.start);
        end_time_button = findViewById(R.id.end);

        submitbutton = (Button) findViewById(R.id.submit);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                //int VId = db.get
                EventName = nameInput.getText().toString();
                EventDes = desInput.getText().toString() ;
                NumPeople = Integer.valueOf(NumPPl.getText().toString());
                StartTime = String.format("%02d:%02d",start_hrs,start_min);
                EndTime = String.format("%02d:%02d",end_hrs,end_min);
                System.out.println(StartTime);
                System.out.println(EndTime);
                //Database db = DatabaseInstance.get_instance();
                //db.add_event(v,EventName,EventDes,NumPeople,StartTime,EndTime);
            }
        });
    }

    public void goToVenue (View view){
        Intent intent = new Intent(this,VenuePage.class);
        startActivity(intent);

    }

    public void StartpopTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener =
                new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHrs, int selectedMin) {
                start_hrs = selectedHrs;
                start_min = selectedMin;

                start_time_button.setText(String.format(Locale.getDefault(),
                        "%02d:%02d",start_hrs,start_min));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                onTimeSetListener, start_hrs,start_min, true);
        timePickerDialog.show();
    }

    public void EndpopTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener =
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHrs, int selectedMin) {
                        end_hrs = selectedHrs;
                        end_hrs = selectedMin;
                        end_time_button.setText(String.format(Locale.getDefault(),
                                "%02d:%02d",end_hrs, end_min));
                    }
                };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                onTimeSetListener, end_hrs,end_min, true);
        timePickerDialog.show();
    }
}
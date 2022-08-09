package com.example.b07_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.b07_project.databinding.ActivityEventDetailPageBinding;

public class EventDetailPage extends AppCompatActivity {

    ActivityEventDetailPageBinding binding;
    String currentEventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_event_detail_page);

        binding = ActivityEventDetailPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //setContentView(R.layout.activity_event_detail_page);

        Intent intent = this.getIntent();


        if (intent != null){


            String name = intent.getStringExtra("name");
            String location = intent.getStringExtra("location");
            String id = intent.getStringExtra("id");
            String desiredPeople = intent.getStringExtra("desired-people");
            String peopleAttending = intent.getStringExtra("people-attending");
            String description = intent.getStringExtra("description");
            String startTime= intent.getStringExtra("start-time");
            String endTime = intent.getStringExtra("end-time");


            binding.detailEventName.setText(name);
            binding.detailEventLocation.setText(location);
            this.currentEventId=id;
            binding.detailEventDesignedPeople.setText(desiredPeople);
            binding.detailCurrentParticipants.setText(peopleAttending);
            binding.detailEventDescription.setText(description);
            binding.detailEventStartTime.setText(startTime);
            binding.detailEventEndTime.setText(endTime);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void goToEvent (View view){
        onBackPressed();
    }

    // send the user to the page if they want to register for this event, use main as default
    // not necessary but nice to have, implement later if theres time
//    public void goEventRegisterPage(View view) {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//    }
}

package com.example.b07_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.b07_project.databinding.ActivityEventDetailPageBinding;

public class EventDetailPage extends AppCompatActivity {

    ActivityEventDetailPageBinding binding;
    String currentEventId;

    Database db = DatabaseInstance.get_instance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventDetailPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();
        System.out.println("In eventdetailpage");


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
    public void register(View view) {
        User u = db.logged_in();
        System.out.println("Join eventing="+u.username);
        Event e = db.get_event(this.currentEventId);
        if (e.maxPP == e.whosGoing.size()) {
            Toast.makeText(EventDetailPage.this, "NOT Joined", Toast.LENGTH_SHORT).show();
        } else {
            db.join_event(this.currentEventId, u);

            Toast.makeText(EventDetailPage.this, "Joined", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
    }
}

package com.example.b07_project;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//import com.example.b07_project.databinding.ActivityEventPageBinding;

import java.util.ArrayList;
import java.util.List;

public class EventPage extends AppCompatActivity {

    ListView eventView;
    ArrayList<Event> events = new ArrayList<Event>();
    EventAdapter adapter;

    //ActivityEventPageBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);

       // binding = ActivityEventPageBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());


        eventView = (ListView) findViewById(R.id.event_view);

        // hard code in list until server is ready
        Venue v1 = new Venue(VenueType.TENNIS_COURT, "totonto", "totonto tennis court",
                1);
        Venue v2 = new Venue(VenueType.BASKETBALL_COURT, "1234 Military Trail, Scarborought, ON, M1A 1C2",
                "a good place to be", 1);

        Event e1 = new Event(v1,5,"tennis","play tennis",
                1, "5pm July 31", "10pm July 31");
        Event e2 = new Event(v1,5,"football","football",
                2, "1pm Aug 21", "4pm Aug 21");
        Event e3 = new Event(v1,12,"random guy concert",
                "concert of a random guy",3, "1am Aug 21",
                "10am Aug 21");
        Event e4 = new Event(v1,12,"weed smoking contest",
                "smoke week and have fun",4, "1am Dec 22",
                "10am Dec 22");
        Event e5 = new Event(v2,12,"swallow kids event",
                "swallow kids and try not to choke",5, "1am Sept 22",
                "10am Sept 22");
        events.add(e1);
        events.add(e2);
        events.add(e3);
        events.add(e4);
        events.add(e5);

//        // use default layout, simple_list_item_1, for each item, customize it later
//        adapter = new ArrayAdapter(EventPage.this, android.R.layout.simple_list_item_1, events);
//        eventView.setAdapter(adapter);

        adapter = new EventAdapter(EventPage.this, events);

        //binding.eventView.setAdapter(adapter);

        // implement below for more clickable events
//        binding.eventView.setClickable(true);
//        binding.eventView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//        };

    }

    // send the user to the last page, use main as default
    public void goToMainPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}

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

import com.example.b07_project.databinding.ActivityEventPageBinding;

import java.util.ArrayList;
import java.util.List;

public class EventPage extends AppCompatActivity {

    ListView eventView;
    ArrayList<Event> events = new ArrayList<Event>();
    EventAdapter adapter;

    ActivityEventPageBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);

        binding = ActivityEventPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        eventView = (ListView) findViewById(R.id.event_view);

        Database db = DatabaseInstance.get_instance();

        events.add(db.get_event(0));
        events.add(db.get_event(1));
        events.add(db.get_event(2));
        events.add(db.get_event(3));
        events.add(db.get_event(4));

//        // use default layout, simple_list_item_1, for each item, customize it later
//        adapter = new ArrayAdapter(EventPage.this, android.R.layout.simple_list_item_1, events);
//        eventView.setAdapter(adapter);

        adapter = new EventAdapter(EventPage.this, events);

        binding.eventView.setAdapter(adapter);

        // implement below for more clickable events
        binding.eventView.setClickable(true);
        binding.eventView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(EventPage.this, EventDetailPage.class);

                intent.putExtra("name", events.get(i).name);
                intent.putExtra("location", events.get(i).location.toString());
                intent.putExtra("id", events.get(i).id);
                intent.putExtra("people-attending", Integer.toString(events.get(i).whos_going.size()));
                intent.putExtra("desired-people", Integer.toString(events.get(i).designedPeople));
                intent.putExtra("description", events.get(i).description);
                intent.putExtra("start-time", events.get(i).eventTime.startTime.toString());
                intent.putExtra("end-time", events.get(i).eventTime.endToString());


                startActivity(intent);

            }
        });

    }

    // send the user to the last page, use main as default
    public void goToMainPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}

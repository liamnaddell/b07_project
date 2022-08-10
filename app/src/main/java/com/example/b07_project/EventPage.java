package com.example.b07_project;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.b07_project.databinding.ActivityEventPageBinding;

import java.util.ArrayList;
import java.util.List;

public class EventPage extends Fragment {

    ListView eventView;
    ArrayList<Event> events = new ArrayList<Event>();
    EventAdapter adapter;

    ActivityEventPageBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_event_page, container, false);

        eventView = (ListView) view.findViewById(R.id.event_view);

        Database db = DatabaseInstance.get_instance();

        events = db.all_events();

//        // use default layout, simple_list_item_1, for each item, customize it later
//        adapter = new ArrayAdapter(EventPage.this, android.R.layout.simple_list_item_1, events);
//        eventView.setAdapter(adapter);

        adapter = new EventAdapter(getActivity(), events);

        eventView.setAdapter(adapter);

        // implement below for more clickable events
        eventView.setClickable(true);
        eventView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(getActivity(), EventDetailPage.class);

                intent.putExtra("name", events.get(i).name);
                intent.putExtra("location", db.get_venue(events.get(i).location).name);
                intent.putExtra("id", events.get(i).eventid);
                intent.putExtra("people-attending", Integer.toString(events.get(i).whosGoing.size()));
                intent.putExtra("desired-people", Integer.toString(events.get(i).maxPP));
                intent.putExtra("description", events.get(i).description);
                intent.putExtra("start-time", events.get(i).eventTime.startTime.toString());
                intent.putExtra("end-time", events.get(i).eventTime.endToString());

                startActivity(intent);

            }
        });
        return view;
    }

    /**
    // send the user to the last page, use main as default
    public void goToMainPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
     */

}

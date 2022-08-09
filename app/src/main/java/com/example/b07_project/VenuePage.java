package com.example.b07_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.b07_project.databinding.ActivityVenuePageBinding;

import java.util.ArrayList;

public class VenuePage<Val> extends AppCompatActivity {

    ListView venueView;
    ArrayList<Venue> venues = new ArrayList<Venue>();
    VenueAdapter venueAdapter;
    ActivityVenuePageBinding Venuebinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_page);

        Venuebinding = ActivityVenuePageBinding.inflate(getLayoutInflater());
        setContentView(Venuebinding.getRoot());

        venueView = (ListView) findViewById(R.id.venue_list);
        Database db = DatabaseInstance.get_instance();
        for (Venue v : db.all_venues()) {
            venues.add(v);
        }

        venueAdapter = new VenueAdapter(VenuePage.this, venues);

        Venuebinding.venueList.setAdapter(venueAdapter);
        Venuebinding.venueList.setClickable(true);
        Venuebinding.venueList.setOnItemClickListener((adapterView, view, position, id) -> {
            Intent intent = new Intent(VenuePage.this, VenueSchedule.class);
            intent.putExtra("eventid",venues.get(position).venueid);
            startActivity(intent);
        });

        Button edit = (Button) findViewById(R.id.edit_button);
        Button done = (Button) findViewById(R.id.done_button);

        boolean check = true;

        if(check){
            done.setVisibility(View.INVISIBLE);
            edit.setVisibility(View.VISIBLE);
        }else{
            edit.setVisibility(View.INVISIBLE);
            done.setVisibility(View.INVISIBLE);
        }


        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edit.setVisibility(View.INVISIBLE);
                done.setVisibility(View.VISIBLE);

            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edit.setVisibility(View.VISIBLE);
                done.setVisibility(View.INVISIBLE);
                //delete.setVisibility(View.INVISIBLE);
            }
        });

    }


    public void goToMain (View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}

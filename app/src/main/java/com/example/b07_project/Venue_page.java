package com.example.b07_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Venue_page extends AppCompatActivity {

    ListView venueView;
    ArrayList<Venue> venuesList = new ArrayList<Venue>();
    VenueAdapter venueAdapter;
    ActivityVenue_pageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);

        binding = ActivityVenue_pageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        venueView = (ListView) findViewById(R.id.venuelist );
        VenueType Basketball = VenueType.BASKETBALL_COURT;
        VenueType Tennis = VenueType.TENNIS_COURT;
        VenueType Football = VenueType.FOOTBALL_FIELD;
        /*
        Database db = DatabaseInstance.get_instance();
        int v1 = db.add_venue(Basketball,"UTSC yard"
                ,"Behind the Alley");
        int v2 = db.add_venue(Tennis,"London","Between Big Ben and Thames");
        int v3 = db.add_venue(Football,"Ha Noi","In Minh's yard");

        venuesList.add(db.get_venue(v1));
        venuesList.add(db.get_venue(v2));
        venuesList.add(db.get_venue(v3));*/

        Venue v1 = new Venue(Basketball,"UTSC","Nice",1);
        venuesList.add(v1);

        venueAdapter = new VenueAdapter(Venue_page.this, venuesList);

        binding.venueView.setClickable(true);
        binding.venueView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("hi");
            }
        });
    }


    public void goToMain (View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}

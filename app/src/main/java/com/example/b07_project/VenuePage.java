package com.example.b07_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.b07_project.databinding.ActivityVenuePageBinding;

import java.util.ArrayList;

public class VenuePage extends AppCompatActivity {

    ListView venueView;
    ArrayList<Venue> venuesList = new ArrayList<Venue>();
    VenueAdapter venueAdapter;
    ActivityVenuePageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_page);

        binding = ActivityVenuePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        venueView = (ListView) findViewById(R.id.venue_list);

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
        Venue v2 = new Venue(Basketball,"Minh's House","Next to" +
                "the Thamse",1);


        venuesList.add(v1);
        venuesList.add(v2);

        venueAdapter = new VenueAdapter(VenuePage.this, venuesList);


        binding.venueList.setAdapter(venueAdapter);

        binding.venueList.setClickable(true);
        binding.venueList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

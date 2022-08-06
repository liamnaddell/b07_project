package com.example.b07_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.b07_project.databinding.ActivityVenuePageBinding;

import java.util.ArrayList;

public class VenuePage extends AppCompatActivity {

    ListView venueView;
    ArrayList<Venue> venuesList = new ArrayList<>();
    VenueAdapter venueAdapter;
    ActivityVenuePageBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_page);

        binding = ActivityVenuePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        venueView = (ListView) findViewById(R.id.venue_list);


        Database db = DatabaseInstance.get_instance();

        venuesList.add(db.get_venue(0));
        venuesList.add(db.get_venue(2));
        for (Venue v: db.all_venues()) {
            venuesList.add(v);

        }

        venueAdapter = new VenueAdapter(VenuePage.this, venuesList);


        binding.venueList.setAdapter(venueAdapter);

        binding.venueList.setClickable(true);
        binding.venueList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //VenueType v = db.get_venue(venuesList.get(position).venueid).type;
                Intent intent = new Intent(VenuePage.this, VenueSchedule.class);
                startActivity(intent);
                
            }
        });
    }


    public void goToMain (View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}

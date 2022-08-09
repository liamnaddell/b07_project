package com.example.b07_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.example.b07_project.databinding.ActivityVenuePageBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class VenuePage extends Fragment {

    ListView venueView;
    ArrayList<Venue> venues = new ArrayList<Venue>();
    VenueAdapter venueAdapter;
    ActivityVenuePageBinding Venuebinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View current_view = inflater.inflate(R.layout.activity_venue_page, container, false);

        venueView = (ListView) current_view.findViewById(R.id.venue_list);

        Venuebinding = ActivityVenuePageBinding.inflate(getLayoutInflater());

        venueView = (ListView) current_view.findViewById(R.id.venue_list);
        Database db = DatabaseInstance.get_instance();
        for (Venue v : db.all_venues()) {
            venues.add(v);
        }

        venueAdapter = new VenueAdapter(getActivity(), venues);

        venueView.setAdapter(venueAdapter);

        Venuebinding.venueList.setClickable(true);
        Venuebinding.venueList.setOnItemClickListener((adapterView, view, position, id) -> {
            Intent intent = new Intent(getActivity(), VenueSchedule.class);
            intent.putExtra("eventid", venues.get(position).venueid);
            startActivity(intent);
        });

        return current_view;
    }




}

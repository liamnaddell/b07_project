package com.example.b07_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.b07_project.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class AvailableVenues extends Fragment {

    ListView venueView;
    ArrayList<Venue> venues = new ArrayList<Venue>();
    VenueAdapter venueAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View current_view = inflater.inflate(R.layout.activity_existing_venues, container, false);

        venueView = (ListView) current_view.findViewById(R.id.venue_list);


        Database db = DatabaseInstance.get_instance();
        for (Venue v : db.all_venues()) {
            venues.add(v);
        }

        venueAdapter = new VenueAdapter(getActivity(), venues);

        venueView.setAdapter(venueAdapter);

        venueView.setClickable(true);
        venueView.setOnItemClickListener((adapterView, view, position, id) -> {
            Intent intent = new Intent(getActivity(), VenueSchedule.class);
            intent.putExtra("venueId", venues.get(position).venueid.toString());
            startActivity(intent);
        });

        return current_view;
    }




}

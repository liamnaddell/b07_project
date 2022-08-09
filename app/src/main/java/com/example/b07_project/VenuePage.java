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

<<<<<<< Updated upstream
=======
        Venuebinding = ActivityVenuePageBinding.inflate(getLayoutInflater());

        venueView = (ListView) current_view.findViewById(R.id.venue_list);
>>>>>>> Stashed changes
        Database db = DatabaseInstance.get_instance();
        for (Venue v : db.all_venues()) {
            venues.add(v);
        }

        venueAdapter = new VenueAdapter(getActivity(), venues);

<<<<<<< Updated upstream
        venueView.setAdapter(venueAdapter);

        venueView.setClickable(true);
        venueView.setOnItemClickListener((adapterView, view, position, id) -> {
=======
        venueAdapter = new VenueAdapter(getActivity(), venues);

        Venuebinding.venueList.setAdapter(venueAdapter);
        Venuebinding.venueList.setClickable(true);
        Venuebinding.venueList.setOnItemClickListener((adapterView, view, position, id) -> {
>>>>>>> Stashed changes
            Intent intent = new Intent(getActivity(), VenueSchedule.class);
            intent.putExtra("eventid",venues.get(position).venueid);
            startActivity(intent);
        });

<<<<<<< Updated upstream
        /*
=======
>>>>>>> Stashed changes
        Button edit = (Button) current_view.findViewById(R.id.edit_button);
        Button done = (Button) current_view.findViewById(R.id.done_button);

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
            }
<<<<<<< Updated upstream
        });*/
=======
        });
>>>>>>> Stashed changes

        return current_view;
    }

    public void goToMain (View view){
        Intent intent = new Intent(getActivity(),MainActivity.class);
        startActivity(intent);
    }

}

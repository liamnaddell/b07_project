package com.example.b07_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class VenueAdapter extends ArrayAdapter<Venue> {
    public VenueAdapter (@NonNull Context context, ArrayList<Venue> VList){
        super (context, R.layout.venue_list_layout,VList);
    }

    @NonNull
    @Override
    public View getView (int position, @NonNull View convertView, @NonNull ViewGroup parent){
        Venue venue = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.venue_list_layout,
                    parent, false);
        }
        //RelativeLayout event_id = convertView.findViewById(R.id.venueID);

        TextView venue_type = convertView.findViewById(R.id.VTYPE);
        TextView venue_loc = convertView.findViewById(R.id.LOC);
        TextView venue_des = convertView.findViewById(R.id.LDES);

        venue_type.setText(venue.type.toString());
        venue_loc.setText(venue.name);
        venue_des.setText(venue.venue_description);


        int i = 1;
        Button delete = convertView.findViewById(R.id.Del);
        //Check to see if user is admin or normal user
        if ( i == 2) {
            delete.setVisibility(View.INVISIBLE);
        }else {
            delete.setVisibility(View.VISIBLE);
        }

        return convertView;

    }
}

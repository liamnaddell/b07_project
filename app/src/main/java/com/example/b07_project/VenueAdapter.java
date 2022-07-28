package com.example.b07_project;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class VenueAdapter extends ArrayAdapter<Venue> {
    public VenueAdapter(Context context, ArrayList<Venue> V_list){
        super(context, R.layout.venue_list, V_list);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Venue venue = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_of_venue,parent,
                    false);
        }
        TextView V_TYPE = convertView.findViewById(R.id.V_TYPE);
        TextView Location = convertView.findViewById(R.id.LOC);
        TextView Des = convertView.findViewById(R.id.Loc_Description);

        V_TYPE.setText(venue.type.toString());
        Location.setText(venue.name);
        Des.setText(venue.venue_description);

        return convertView;
    }
}

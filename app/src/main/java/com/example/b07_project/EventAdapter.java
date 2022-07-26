package com.example.b07_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class EventAdapter extends ArrayAdapter<Event> {
    public EventAdapter(@NonNull Context context, ArrayList<Event> eList) {
        super(context, R.layout.event_list_layout, eList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        Event event = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_list_layout, parent, false);
        }

//        LinearLayout event_id = convertView.findViewById(R.id.eventID);
        TextView event_name = convertView.findViewById(R.id.eventName);
        TextView event_startTime = convertView.findViewById(R.id.eventStartTime);
        TextView event_endTime = convertView.findViewById(R.id.eventEndTime);
        TextView event_location = convertView.findViewById(R.id.location);
        TextView event_designedPeople = convertView.findViewById(R.id.designedPeople);
        Database db = DatabaseInstance.get_instance();

//        event_id.setId(event.id);
        event_name.setText(event.name);
        event_startTime.setText(event.eventTime.startTime + " - ");
        event_endTime.setText(event.eventTime.endToString());
        event_location.setText(db.get_venue(event.location).name);
        event_designedPeople.setText(Integer.toString(event.maxPP) + " People");

        return convertView;
    }
}

package com.example.b07_project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class UserFragmentMyEvents extends Fragment {
    ListView my_events_list;
    ArrayList<Event> my_events = new ArrayList<Event>();
    EventAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_user_page_myevents, container, false);
        Database db = DatabaseInstance.get_instance();
        /**
         * for when we have a database of what events each user has scheduled
         int i = 0;
         while(db.get_event(i) != null){
         my_events.add(db.get_event(i));
         i++;
         }*/
        my_events.add(db.get_event(0));
        my_events.add(db.get_event(1));
        my_events.add(db.get_event(2));
        my_events.add(db.get_event(3));
        my_events.add(db.get_event(4));
        my_events_list = (ListView) view.findViewById(R.id.my_event_view);
        adapter = new EventAdapter(getActivity(), my_events);
        my_events_list.setAdapter(adapter);
        return view;
    }
}

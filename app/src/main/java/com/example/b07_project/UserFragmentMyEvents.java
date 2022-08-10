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
    ArrayList<Event> my_events;
    EventAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_user_page_myevents, container, false);
        Database db = DatabaseInstance.get_instance();
        //my_events should contain the events that the user has joined
        User temp = db.logged_in();

        // this line does not work until getUserRegisteredEvents is implemented in firebasedb
        // user is not avaliable until that is implemented
        my_events = db.getUserRegisteredEvents(temp);
        my_events_list = (ListView) view.findViewById(R.id.my_event_view);
        adapter = new EventAdapter(getActivity(), my_events);
        my_events_list.setAdapter(adapter);
        return view;
    }
}

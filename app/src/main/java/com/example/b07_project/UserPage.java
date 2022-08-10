package com.example.b07_project;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.b07_project.ui.main.SectionsPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class UserPage extends Fragment {
    ListView my_events_list;
    ArrayList<Event> my_events;
    EventAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_user_page, container, false);
        Database db = DatabaseInstance.get_instance();
        //my_events should contain the events that the user has joined
        User temp = db.logged_in();

        // this line does not work until getUserRegisteredEvents is implemented in firebasedb
        // user is not avaliable until that is implemented
        my_events = db.getUserScheduledEvents(temp);


        my_events_list = (ListView) view.findViewById(R.id.my_event_view);
        adapter = new EventAdapter(getActivity(), my_events);
        my_events_list.setAdapter(adapter);
        signout_handler(view);
        return view;
    }
    public void addVenuesOnClick(View v) {
    }
    public void venue_submit_handler(View view) {
        System.out.println("Venue Submit Handler");
        return;
    }

    public void signout_handler(View view) {
        Button btnLogOut = view.findViewById(R.id.sign_out_button);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            FirebaseAuth mAuth;
            @Override
            public void onClick(View v) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                Intent intent = new Intent(getActivity(), LoginPage.class);
                SectionsPagerAdapter.wasset = false;
                startActivity(intent);
            }
        });
    }

}
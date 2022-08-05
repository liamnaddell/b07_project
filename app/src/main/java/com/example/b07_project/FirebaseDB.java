package com.example.b07_project;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Vector;

public class FirebaseDB implements Database {
    FirebaseAuth mAuth;
    public FirebaseDB() {
        mAuth = FirebaseAuth.getInstance();
        return;
    }
    //checks that a user with username and password password exists in the database
    public User login(String username, String password) {
        //jank, sorry about this lmao
        Vector v = new Vector();
        mAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    v.add(true);
                } else {
                    v.add(false);
                }
            }
        });
        while (v.size() != 1) {}
        if ((boolean) v.get(0) == true) {
            //how do I get if the user is an admin?
            return new User(username,password,false);
        }
        return null;
    }
    public User logged_in() {
        //how do I implement this?
        return null;
    }
    public boolean is_admin(String username) {
        return false;
    }

    @Override
    public User find_user_by_name(String username) {
        return null;
    }

    //adds user with username, password into the database, returns true if successful
    public boolean add_user(String username, String password, boolean is_admin) {
        return false;
    }
    public int add_event(Venue v, String event_name, String event_description, int num_people) {
        return 0;
    }
    public Event get_event(int eventid) {
        return null;
    }
    public int add_venue(VenueType vt, String venue_name, String venue_description) {
        return 0;
    }
    public Venue get_venue(int venueid) {
        return null;
    }
    public void join_event(int eventid, User user) {
        return;
    }

    @Override
    public int add_event(int venueid, String event_name, String event_description, int num_people, String event_start_time, String event_end_time) {
        return 0;
    }

    @Override
    public ArrayList<Event> getUserRegisteredEvents(User user) {
        return null;
    }
}

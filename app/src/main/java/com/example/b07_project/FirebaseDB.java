package com.example.b07_project;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class FirebaseDB implements Database {
    
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    FirebaseUser user;

    public FirebaseDB() {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    //checks that a user with username and password password exists in the database
    // return user if login successful, else return null
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

        if ((boolean) v.get(0) == true) {
            return new User(username,password,this.is_admin(username));
        }
        return null;
    }

    public User logged_in() {
        //how do I implement this?

        //what does this method do?

        return null;
    }

    public boolean is_admin(String username) {
        return find_user_by_name(username).isAdmin;
    }

    // return user given username, return null if user not found
    @Override
    public User find_user_by_name(String username) {
        Task<QuerySnapshot> b = db.collection("users")
                .whereEqualTo("username",username)
                .get();

        while (!b.isComplete()) {};

        if (b.isSuccessful()) {
            System.out.println("done");
            return b.getResult().toObjects(new User().getClass()).get(0);

        } else {
            System.out.println("BAD FIREBASE QUERY LMAOOAO");
        }
        return null;
    }

    //adds user with username, password into the database, returns true if successful
    //assume user is created in firebase authentication
    public boolean add_user(String username, String password, boolean is_admin) {
        //fix:userid
        //to tharuth, put uid instead of username here maybe
        Task<DocumentReference> t = db.collection("users").add(new User(username,password,is_admin));

        while (!t.isComplete()) {}

        if (t.isSuccessful()) return true;
        return false;


    }

    // get event from database, return null if event dont exist
    public Event get_event(int eventid) {
        //fix:serialzable,array in events
        Task<QuerySnapshot> b = db.collection("events")
                .whereEqualTo("eventid",eventid)
                .get();

        while (!b.isComplete()) {};

        if (b.isSuccessful()) {
            System.out.println("done");
            return b.getResult().toObjects(new Event().getClass()).get(0);
        } else {
            System.out.println("BAD FIREBASE QUERY LMAOOAO");
        }
        return null;
    }

    @Override
    public int add_venue(VenueType vt, String venue_name, String venue_description) {
        //fix: return venueid,serializable
        Task<DocumentReference> t = db.collection("venues").add(new Venue(vt,venue_name,venue_description));

        while (!t.isComplete()) {}

        if (t.isSuccessful()) return 1;
        return 0;
    }

    // return venue by id
    public Venue get_venue(int venueid) {
        //fix:serialzable
        Task<QuerySnapshot> b = db.collection("events")
                .whereEqualTo("eventid",venueid)
                .get();

        while (!b.isComplete()) {};

        if (b.isSuccessful()) {
            System.out.println("done");
            return b.getResult().toObjects(new Venue().getClass()).get(0);
        } else {
            System.out.println("BAD FIREBASE QUERY LMAOOAO");
        }
        return null;
    }

    @Override
    public void join_event(int eventid, User user) {
        //implement
    }

    // add event to server, return 1 if successful
    public int add_event(int venueid, String event_name, String event_description, int num_people,
                         TimeSlot startTime, int duration, int day) {
        //fix:implement
        return 0;
    }

    // return an arraylist of venues stored in database, return empty arraylist if none found
    public ArrayList<Venue> all_venues() {

        ArrayList<Venue> venues = new ArrayList<>();

        db.collection("venues")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String name;
                            VenueType type;
                            String description;
                            Integer vid;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                name= document.get("name").toString();
                                type = VenueType.valueOf(document.get("type").toString());
                                description = document.get("description").toString();
                                vid = Integer.parseInt(document.getId());
                                venues.add(new Venue(type, name, description, vid));
                            }
                        }
                    }
                });

        return venues;
    }

    @Override
    public ArrayList<Event> getUserRegisteredEvents(User user) {
        return null;
    }
}

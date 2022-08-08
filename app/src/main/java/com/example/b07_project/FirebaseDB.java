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

        final int[] suc = {0};
        User u = new User(username, password, is_admin);
        Map<String, Object> user_info = new HashMap<>();

        user_info.put("username", u.username);
        user_info.put("password", u.password);
        user_info.put("isAdmin", u.is_admin);

        Log.d("user creation", "1");

        firestore.collection("users")
                .add(user_info)
                .addOnSuccessListener( new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        suc[0] = 1;
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("user creation", "3");
                        Log.d("Add_user", "failed create new user database");
                        suc[0] = 0;
                    }
                });

        Log.d("user creation", "4");
        return Boolean.parseBoolean(Integer.toString(suc[0]));

    }

    // get event from database, return null if event dont exist
    public Event get_event(int eventid) {
        DocumentReference docRef = firestore.collection("events").document(Integer.toString(eventid));

        final Event[] e = new Event[1];
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()){
                        String name = document.get("name").toString();
                        String desc = document.get("description").toString();
                        String startTime = document.get("start_time").toString();
                        String endTime = document.get("end_time").toString();
                        Integer maxPP = Integer.parseInt(document.get("max_user").toString());
                        Venue v = get_venue(Integer.parseInt(document.get("venue_id").toString()));
                        ArrayList<Integer> registeredUsers = new ArrayList<>();

                        for (String userid: (ArrayList<String>)document.get("registered_user_id")){
                            registeredUsers.add(Integer.parseInt(userid));
                        }

                        e[0] = new Event(v,maxPP,name,desc,Integer.parseInt(document.getId().toString()),
                                startTime,endTime);

                    }
                    else{
                        e[0] = null;
                    }
                }
            }
        });
        return e[0];
    }

    @Override
    public int add_venue(VenueType vt, String venue_name, String venue_description) {
        return 0;
    }

    // add venue to database, return 1 if success, 0 otherwise
    public int add_venue(VenueType vt, String venue_name, String venue_description, int venue_id) {

        final int[] suc = {0};
        //Venue v = new Venue(username, password, is_admin);
        Map<String, Object> venue_info = new HashMap<>();

        venue_info.put("name", venue_name);
        venue_info.put("type", vt);
        vdenue_info.put("description", venue_description);

        firestore.collection("venues")
                .document(Integer.toString(venue_id))
                .set(venue_info)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            suc[0] = 1;
                        }
                    }
                });

        if (suc[0] == 1){
            return suc[0];
        }
        return suc[1];
    }

    // return venue by id
    public Venue get_venue(int venueid) {
        DocumentReference docRef = firestore.collection("venues").document(Integer.toString(venueid));
        final String[] name = new String[1];
        final String[] description = new String[1];
        final VenueType[] type = new VenueType[1];
        final Boolean[] suc = new Boolean[1];
        suc[0] = false;

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("get_venue", "DocumentSnapshot data: " + document.getData());
                        suc[0] = true;
                        name[0] = document.get("name").toString();
                        description[0] = document.get("descriptioon").toString();
                        type[0] = VenueType.valueOf(document.get("type").toString());

                    } else {
                        Log.d("get_venue", "No such document");
                    }
                } else {
                    Log.d("get_venue", "get failed with ", task.getException());
                }
            }
        });

        if (suc[0] == true){
            return new Venue(type[0],name[0],description[0], venueid);
        }
        return null;
    }

    @Override
    public void join_event(int eventid, User user) {
    }

    // add event to server, return 1 if successful
    public void add_event(int venueid, String event_name, String event_description, int num_people,
                 EventTime et) {
    }

    // return an arraylist of venues stored in database, return empty arraylist if none found
    public ArrayList<Venue> all_venues() {

        ArrayList<Venue> venues = new ArrayList<>();

        firestore.collection("venues")
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

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
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    //this method works
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

    //fix me tharuth :)
    public boolean add_user(String username, String password, boolean is_admin) {
        //fix:userid
        //to tharuth, put uid instead of username here maybe
        Task<Void> t = db.collection("users").document(username)
                .set(new User(username,password,is_admin));

        while (!t.isComplete()) {}

        if (t.isSuccessful()) return true;
        return false;
    }

    public String getUserId(User user){
        Task<QuerySnapshot> query = db.collection("users").whereEqualTo("username", user.username).get();

        while (!query.isComplete()){}

        if (!query.isSuccessful()){
            Log.d("idiot", "failed search for id");
        }
        Log.d("gj", "userId found");
        String id;
        for (QueryDocumentSnapshot doc: query.getResult()){
            return doc.getId();
        }
        return null;
    }

    //this works
    public Event get_event(String eventid) {
        System.out.println("Stariting getevent query");
        Task<DocumentSnapshot> b = db.collection("events").document(eventid).get();

        while (!b.isComplete()) {};

        if (b.isSuccessful()) {
            System.out.println("done w/ getevent query");
            Event e = b.getResult().toObject(new Event().getClass());
            e.eventid=(b.getResult().getId());
            return e;
        } else {
            System.out.println("BAD FIREBASE QUERY LMAOOAO");
        }
        return null;
    }

    public String add_venue(Venue v) {
        DocumentReference ref = db.collection("venues").document();

        Task<Void> t = ref.set(v);
        while (!t.isComplete()) {}

        if (t.isSuccessful()) {
            return ref.getId();
        } else {
            return null;
        }
    }

    //this method functions correctly
    public Venue get_venue(String venueid) {
        //fix:serialzable
        Task<DocumentSnapshot> b = db.collection("venues").document(venueid).get();

        while (!b.isComplete()) {};

        if (b.isSuccessful()) {
            System.out.println("done");
            Venue v = (Venue) b.getResult().toObject(new Venue().getClass());
            v.venueid = b.getResult().getId();
            return v;
        } else {
            System.out.println("BAD FIREBASE QUERY LMAOOAO");
        }
        return null;
    }

    @Override
    public void join_event(String eventid, User user) {
        String userId;
        Task<QuerySnapshot> query = db.collection("users").whereEqualTo("username", user.username).get();

        while (!query.isComplete()){}

        if (!query.isSuccessful()){
            Log.d("idiot", "failed to join event");
        }
        userId = query.toString();
        //Log.d("join_event", "userid = " + userId);

        DocumentReference ref = db.collection("events").document(eventid);
        ArrayList<String> participants = new ArrayList<>();
        DocumentSnapshot doc = ref.get().getResult();
        if (doc.exists()){
//            participants = (ArrayList<String>)doc.get("whosGoing");
//            participants.add(userId);
            ref.update("whosGoing", FieldValue.arrayUnion("userId"));
            Log.d("join_event","joined");
        }
        else{
            Log.d("idiot","joined");
        }
//        Map<String,Object> hashMap = new HashMap<>();
//        hashMap.put("whosGoing",hashMap);
//        db.collection("events").document(eventid).update(hashMap);
    }

    // add event to server, return 1 if successful
    public String add_event(Event e) {

        DocumentReference ref = db.collection("events").document();

        Task<Void> t = ref.set(e);
        while (!t.isComplete()) {}

        if (t.isSuccessful()) return ref.getId();
        else return null;
    }

    // return an arraylist of venues stored in database, return empty arraylist if none found
    public ArrayList<Venue> all_venues() {

        ArrayList<Venue> venues = new ArrayList<>();

        Task<QuerySnapshot> query = db.collection("venues")
                .get();

        while(!query.isComplete()){}

        for (QueryDocumentSnapshot document: query.getResult()){
            Venue v = document.toObject(new Venue().getClass());
            v.venueid = document.getId().toString();
            venues.add(v);
        }

        return venues;
    }
    // return an arraylist of venues stored in database, return empty arraylist if none found
    public ArrayList<Event> all_events() {
        //fix later
        ArrayList<Event> events = new ArrayList<>();

        Task<QuerySnapshot> query = db.collection("events").get();

        while (!query.isComplete()){}

        for (QueryDocumentSnapshot document: query.getResult()){
            Event e = document.toObject(new Event().getClass());
            e.eventid = document.getId();
            events.add(e);
        }

        return events;
    }

    @Override
    public ArrayList<Event> getUserRegisteredEvents(User user) {
        ArrayList<Event> events = new ArrayList<>();

        String userId = getUserId(user);
        Log.d("get user evenets id", userId);
        Task<QuerySnapshot> query = db.collection("events").whereArrayContains("whosGoing",userId).get();

        while (!query.isComplete()){}

        if (query.isSuccessful()) {
            for (QueryDocumentSnapshot document : query.getResult()) {
                Log.d("get user evenets", document.getData().toString());
                Event e = document.toObject(new Event().getClass());
                e.eventid = document.getId();
                events.add(e);
            }
        }
        else{
            Log.d("get user evenets", "failed");
        }

        Log.d("get user evenets", "123");
        return events;
    }

    public void delete_venue(String vid) {
        Task<QuerySnapshot> query = db.collection("events")
                .whereEqualTo("location",vid)
                .get();

        while (!query.isComplete()){}

        for (QueryDocumentSnapshot document: query.getResult()){
            document.getReference().delete();
        }
        Task<DocumentSnapshot> ds = db.collection("venues").document("vid").get();
        while (!ds.isComplete()) {}
        if (ds.isSuccessful()) {
            ds.getResult().getReference().delete();
        }
    }
}

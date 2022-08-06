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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class FirebaseDB implements Database {
    
    FirebaseFirestore firestore;
    FirebaseAuth mAuth;
    FirebaseUser user;
    public FirebaseDB() {
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        return;
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
        final Boolean[] isAdmin = new Boolean[1];
        firestore.collection("users")
                .whereEqualTo("isAdmin", false)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            System.out.println("123123123" + "||||" + task.getResult().size());

                            for (QueryDocumentSnapshot document: task.getResult()){
                                System.out.println("12312312312322");
                                if (document.get("username").toString() == username){
                                    isAdmin[0] = true;
                                }
                                else{
                                    isAdmin[0] = false;
                                }
                            }
                        }
                    }
                });
        return isAdmin[0];
    }

    // return user given username, return null if user not found
    @Override
    public User find_user_by_name(String username) {

        final User user;
        final String[] password = new String[1];
        final Boolean[] isAdmin = new Boolean[1];
        firestore.collection("users")
                .whereEqualTo("username", username)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            System.out.println("123123123" + "||||" + task.getResult().size());

                            for (QueryDocumentSnapshot document: task.getResult()){
                                System.out.println("12312312312322");
                                if (document.get("username").toString() == username) {
                                    password[0] = document.get("password").toString();
                                    isAdmin[0] = Boolean.parseBoolean(document.get("isAdmin").toString());
                                }
                            }
                        }
                    }
                });

        if (password.length == 0){
            return null;
        }
        return new User(username,password[0],isAdmin[0]);
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
    public Venue[] all_venues() {
        return null;
    }

    @Override
    public ArrayList<Event> getUserRegisteredEvents(User user) {
        return null;
    }
}

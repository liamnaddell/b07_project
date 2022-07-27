package com.example.b07_project;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class MockDB implements Database {
    //somebody could probably make this better
    //users[0] has salt salts[0], password passwords[0]
    ArrayList<User> users;
    ArrayList<Event> events;
    ArrayList<Venue> venues;
    public MockDB() {
        users = new ArrayList<User>();
        events = new ArrayList<Event>();
        venues = new ArrayList<Venue>();
    }
    public User check_pw(String username,String password) {
        for (User user: users) {
            if (user.username.equals( username) && user.password.equals(password)) {
                return user;
            }
        }
        return null;
    }
    public void add_user(String username, String password, boolean is_admin) {
        User user = new User(username,password,is_admin);
        this.users.add(user);
        return;

    }

    public boolean is_admin(String username)  {
        for (User user: users) {
            if (user.username.equals(username)) {
                return user.is_admin;
            }
        }
        return false;
    }
    public int add_event(Venue v, String event_name, String event_description, int num_people) {
        int eventid = events.size();
        Event e = new Event(v,num_people,event_name,event_description,eventid);
        events.add(e);
        return eventid;
    }
    public Event get_event(int eventid) {
        return events.get(eventid);
    }
    public int add_venue(VenueType vt, String event_name, String event_description) {
        int venueid=venues.size();
        Venue v = new Venue(vt,event_name,event_description,venueid);
        venues.add(v);

        return venueid;
    }
    public Venue get_venue(int venueid) {
        return venues.get(venueid);
    }
    public void join_event(int eventid, User user) {
        return;
    }
}


package com.example.b07_project;

import java.lang.reflect.Array;
import java.util.ArrayList;

//use sql solution for event ids, 
public interface Database {
    //checks that a user with username and password password exists in the database
    public User login(String username, String password);
    public User logged_in();
    public User find_user_by_name(String username);
    //adds user with username, password into the database, returns true if successful
    public boolean add_user(String username, String password, boolean is_admin);
    //duration = how many 30-minute TimeSlot's
    public int add_event(Event e);
    public ArrayList<Event> getUserRegisteredEvents(User user);
    public Event get_event(int eventid);
    public int add_venue(Venue v);
    public Venue get_venue(int venueid);
    public void join_event(int eventid, User user);
    public ArrayList<Venue> all_venues();
    public ArrayList<Event> all_events();
    //delete venue and maybe all events at that venue if we have tiem
    public void delete_venue();
}
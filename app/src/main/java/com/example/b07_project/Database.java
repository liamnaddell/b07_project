package com.example.b07_project;

import java.util.ArrayList;

public interface Database {
    //checks that a user with username and password password exists in the database
    public User login(String username, String password);
    public User logged_in();
    public User find_user_by_name(String username);
    //adds user with username, password into the database, returns true if successful
    public boolean add_user(String username, String password, boolean is_admin);
    //duration = how many 30-minute TimeSlot's
    public int add_event(int venueid, String event_name, String event_description, int num_people,
                         TimeSlot start_time, int duration, String date);
    public ArrayList<Event> getUserRegisteredEvents(User user);
    public Event get_event(int eventid);
    public int add_venue(VenueType vt, String venue_name, String venue_description);
    public Venue get_venue(int venueid);
    public void join_event(int eventid, User user);
    public Venue[] all_venues();
}
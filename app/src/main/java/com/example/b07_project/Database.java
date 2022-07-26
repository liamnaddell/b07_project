package com.example.b07_project;

public interface Database {
    //checks that a user with username and password password exists in the database
    public User check_pw(String username, String password);
    public boolean is_admin(String username);
    //adds user with username, password into the database
    public void add_user(String username, String password, boolean is_admin);
    public int add_event(Venue v, String event_name, String event_description, int num_people);
    public Event get_event(int eventid);
    public int add_venue(VenueType vt, String venue_name, String venue_description);
    public Venue get_venue(int venueid);
    public void join_event(int eventid, User user);
}
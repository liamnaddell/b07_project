package com.example.b07_project;

import java.util.ArrayList;

public class MockDB implements Database {
    //somebody could probably make this better
    //users[0] has salt salts[0], password passwords[0]
    ArrayList<User> users;
    ArrayList<Event> events;
    ArrayList<Venue> venues;
    ArrayList<Event> userRegisteredEvents;
    User logged_in;

    public MockDB() {
        users = new ArrayList<User>();
        events = new ArrayList<Event>();
        venues = new ArrayList<Venue>();
    }
    public User login(String username,String password) {
        for (User user: users) {
            if (user.username.equals( username) && user.password.equals(password)) {
                logged_in=user;
                return user;
            }
        }
        return null;
    }

    public User logged_in() {
        return logged_in;
    }

    public boolean add_user(String username, String password, boolean is_admin) {
        if (!users.contains(username)) {
            User user = new User(username, password, is_admin);
            this.users.add(user);
            return true;
        }
        return false;

    }

    @Override
    public User find_user_by_name(String username) {
        for(User u : users){
            if(u.username.equals(username)){
                return u;
            }
        }
        return null;
    }

    // For the time, convert it into string of format "yyyy-mm-dd hh-mm-ss"
    // for example: 2021 july 27 11pm 32 min 51 second would be: "2021-07-27 23-32-51"
    public int add_event(int venueid, String event_name, String event_description, int num_people,
                         TimeSlot ts, int duration) {
        int eventid = events.size();
        Event e = new Event(get_venue(venueid),num_people,event_name,event_description,eventid,new EventTime(ts,
                duration));
        events.add(e);
        return eventid;
    }

    public Event get_event(int eventid) {
        return events.get(eventid);
    }
    public int add_venue (VenueType vt, String event_name, String event_description) {
        int venueid=venues.size();
        Venue v = new Venue(vt,event_name,event_description,venueid);
        venues.add(v);

        return venueid;
    }
    public Venue get_venue(int venueid) {
        return venues.get(venueid);
    }

    public void join_event(int eventid2, User user) {
        events.get(eventid2).addUserToEvent(user);
    }

    // return an ArrayList of events that is registered by the user
    public ArrayList<Event> getUserRegisteredEvents(User user){

        userRegisteredEvents = new ArrayList<Event>();
        for (Event event: events){
            if (event.isUserRegistered(user)) {
                userRegisteredEvents.add(event);
            }
        }
        return userRegisteredEvents;
    }
    public ArrayList<Venue> all_venues() {
//        Venue[] vs = new Venue[this.venues.size()];
//        vs=venues.toArray(vs);
        return this.venues;
    }
}


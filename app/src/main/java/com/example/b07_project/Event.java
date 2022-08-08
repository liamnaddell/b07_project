package com.example.b07_project;

import java.util.ArrayList;
import java.io.Serializable;

public class Event implements Serializable {

        // locations should be class Venue, use String for now
        // whos_going should consist of class User, use String for now

    public int eventid;
    public String name;
    public String description;

    public int maxPP;
    public EventTime eventTime;
    public Venue location;
    public ArrayList<User> whos_going;

    public Event() {
            return;
        }

        // use this constructor to create new event only
        public Event(Venue v, int num_people, String event_name, String event_description,
                     int eventid, EventTime et) {

            this.eventid = eventid;
            this.name = event_name;
            this.whos_going = new ArrayList<User>();
            this.description = event_description;

            this.maxPP = num_people;
            this.eventTime=et;
            this.location = v;
        }

        // check if user is registered for this event, return true if user already registered, false otherwise
        public boolean isUserRegistered(User user) {
            return this.whos_going.contains(user);
        }

        // add user to event, return true if succeeded
        //NOT A DATABASE METHOD
        public boolean addUserToEvent(User user){

            if (this.isUserRegistered(user)){
                return false;
            }
            this.whos_going.add(user);
            return true;
        }
}

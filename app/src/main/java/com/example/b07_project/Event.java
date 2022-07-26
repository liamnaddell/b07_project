package com.example.b07_project;

import java.util.ArrayList;
import java.io.Serializable;

public class Event implements Serializable {

        // locations should be class Venue, use String for now
        // whos_going should consist of class User, use String for now

    String eventid;
    public String name;
    public String description;

    public int maxPP;
    public EventTime eventTime;
    public String location;
    public ArrayList<String> whosGoing;
    public String scheduler;

    public Event() {
    }

        // use this constructor to create new event only
        public Event(Venue v, int num_people, String event_name, String event_description,
                     String eventid, EventTime et, String scheduler) {

            this.eventid = eventid;
            this.name = event_name;
            this.whosGoing = new ArrayList<String>();
            this.description = event_description;

            this.maxPP = num_people;
            this.eventTime=et;
            this.location = v.venueid;
            this.scheduler = scheduler;
        }

        // check if user is registered for this event, return true if user already registered, false otherwise
        public boolean isUserRegistered(User user) {
            return this.whosGoing.contains(user);
        }

        // add user to event, return true if succeeded
        //NOT A DATABASE METHOD
        public boolean addUserToEvent(User user){

            if (this.isUserRegistered(user)){
                return false;
            }
            this.whosGoing.add(user.username);
            return true;
        }

    @Override
    public String toString() {
        Database db = DatabaseInstance.get_instance();
        return db.get_venue(this.location).name;
    }
}

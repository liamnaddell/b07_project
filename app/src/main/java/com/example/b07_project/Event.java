package com.example.b07_project;

import java.util.ArrayList;

public class Event {

        // locations should be class Venue, use String for now
        // whos_going should consist of class User, use String for now

        int id;
        static int event_count = 0;

        ArrayList<User> whos_going;
        String name;
        String description;

        int designedPeople;
        EventTime eventTime;
        Venue location;

        // use this constrctor to load existing event from database
        public Event(Venue v, int num_people, String event_name, String event_description,
                     TimeSlot ts, int duration, int day) {

            this.id = Event.event_count;
            Event.event_count++;
            this.name = event_name;
            this.whos_going = new ArrayList<User>();
            this.description = event_description;

            this.designedPeople = num_people;
            this.eventTime = new EventTime(ts,duration,day);
            this.location = v;
        }

        // use this constructor to create new event only
        public Event(Venue v, int num_people, String event_name, String event_description,
                     int eventid, EventTime et) {

            this.id = eventid;
            this.name = event_name;
            this.whos_going = new ArrayList<User>();
            this.description = event_description;

            this.designedPeople = num_people;
            this.eventTime=et;
            this.location = v;
        }

        // check if user is registered for this event, return true if user already registered, false otherwise
        public boolean isUserRegistered(User user) {
            return this.whos_going.contains(user);
        }

        // add user to event, return true if succeeded
        public boolean addUserToEvent(User user){

            if (this.isUserRegistered(user)){
                return false;
            }
            this.whos_going.add(user);
            return true;
        }
}

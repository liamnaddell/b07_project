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
        String startTime;
        String endTime;
        String location;

        // use this constrctor to load existing event from database
        public Event(Venue v, int num_people, String event_name, String event_description,
                     String startTime, String endTime) {

            this.id = Event.event_count;
            Event.event_count++;
            this.name = event_name;
            this.whos_going = new ArrayList<User>();
            this.description = event_description;

            this.designedPeople = num_people;
            this.startTime = startTime;
            this.endTime = endTime;
            this.location = v.get_venue_name();
        }

        // use this constructor to create new event only
        public Event(Venue v, int num_people, String event_name, String event_description,
                     int eventid, String startTime, String endTime) {

            this.id = eventid;
            this.name = event_name;
            this.whos_going = new ArrayList<User>();
            this.description = event_description;

            this.designedPeople = num_people;
            this.startTime = startTime;
            this.endTime = endTime;
            this.location = v.get_venue_name();
        }

        // check if user is registered for this event, return true if user already registered, false otherwise
        public boolean isUserRegistered(User user) {
            return this.getEventParticipants().contains(user);
        }

        // add user to event, return true if succeeded
        public boolean addUserToEvent(User user){

            if (this.isUserRegistered(user)){
                return false;
            }
            this.whos_going.add(user);
            return true;
        }

        public int getEventId(){
            return this.id;
        }

        public String getEventName(){
            return this.name;
        }

        public String getEventDescription(){
            return this.description;
        }

        public String getEventVenue(){
            return this.location;
        }
        public String getEventStartTime(){
            return this.startTime;
        }
        public String getEventEndTime(){
            return this.endTime;
        }

        public ArrayList<User> getEventParticipants(){
            return this.whos_going;
        }
        public String getEventMaxParticipants(){
            return this.description;
        }

}

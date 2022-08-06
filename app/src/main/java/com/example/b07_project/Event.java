package com.example.b07_project;

import java.util.ArrayList;

public class Event {

        // locations should be class Venue, use String for now
        // whos_going should consist of class User, use String for now

        int id;

        ArrayList<User> whos_going;
        String name;
        String description;

        int designedPeople;
        EventTime et;
        Venue location;


        public Event(Venue v, int num_people, String event_name, String event_description,
                     int eventid, EventTime et) {

            this.id = eventid;
            this.name = event_name;
            this.whos_going = new ArrayList<User>();
            this.description = event_description;

            this.designedPeople = num_people;
            this.et=et;
            this.location = v;
        }

//         return an array in format of [id, name, describtion, venue, start time, end time, people
//         attending, max people], all converted into string
//        public String getEventInfo(){
//
//            return [this.getEventId(), this.getEventName(), this.getEventDescription(),this.getEventStartTime(),
//            this.getEventEndTime(),this.getEventParticipants().size(), this.getEventMaxParticipants()];
//        }

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

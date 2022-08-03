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
        String startTime;
        String endTime;
        String location;


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

//         return an array in format of [id, name, describtion, venue, start time, end time, people
//         attending, max people], all converted into string
//        public String getEventInfo(){
//
//            return [this.getEventId(), this.getEventName(), this.getEventDescription(),this.getEventStartTime(),
//            this.getEventEndTime(),this.getEventParticipants().size(), this.getEventMaxParticipants()];
//        }

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

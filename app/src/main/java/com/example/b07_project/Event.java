package com.example.b07_project;

import java.util.ArrayList;

public class Event {

        // locations should be class Venue, use String for now
        // whos_going should consist of class User, use String for now

        int id;

        ArrayList<String> whos_going;
        String name;
        String description;

        int designedPeople;
        String startTime;
        String endTime;
        String location;

        public Event(String v, int num_people, String event_name, String event_description,
                     int eventid, String startTime, String endTime) {

            this.id = eventid;
            this.name = event_name;
            this.whos_going = new ArrayList<String>();
            this.description = event_description;

            this.designedPeople = num_people;
            this.startTime = startTime;
            this.endTime = endTime;
            this.location = v;
        }



}

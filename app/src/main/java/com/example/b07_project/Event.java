package com.example.b07_project;

import java.util.ArrayList;

public class Event {
    Venue v;
    //the number of people currently going, must be less than or equal to v.get_capacity()
    int num_people;
    String event_name;
    String event_description;
    int eventid;
    ArrayList<User> whos_going;
    public Event(Venue v,int num_people,String event_name,String event_description,int eventid) {
        v=v;
        num_people=num_people;
        event_name=event_name;
        event_description=event_description;
        eventid=eventid;
        whos_going=new ArrayList<User>();
    }

}
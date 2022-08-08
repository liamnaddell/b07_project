package com.example.b07_project;

import java.io.Serializable;

public class EventTime implements Serializable {
    public String day;
    public TimeSlot startTime;
    //30 minute time-slots used
    public int duration;
    public EventTime(TimeSlot startTime, int duration,String day) {
        this.startTime=startTime;
        this.duration=duration;
        this.day=day;
    }
    public EventTime() {
    }

    public String endToString() {
        TimeSlot ets = new TimeSlot(startTime.slotNo+duration);
        return ets.toString();
    }
    @Override
    public String toString() {
        return day+" at "+startTime+"-"+endToString();

    }
}

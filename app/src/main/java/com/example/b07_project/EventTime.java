package com.example.b07_project;

public class EventTime {
    TimeSlot startTime;
    //30 minute time-slots used
    int duration;
    public EventTime(TimeSlot startTime, int duration) {
        this.startTime=startTime;
        this.duration=duration;
    }
    public String endToString() {
        TimeSlot ets = new TimeSlot(startTime.slot_no+duration);
        return ets.toString();
    }
    @Override
    public String toString() {
        return startTime+"-"+endToString();
    }
}

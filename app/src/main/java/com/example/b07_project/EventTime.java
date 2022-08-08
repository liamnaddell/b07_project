package com.example.b07_project;

public class EventTime {
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
        TimeSlot ets = new TimeSlot(startTime.slot_no+duration);
        return ets.toString();
    }
    @Override
    public String toString() {
        return day+" at "+startTime+"-"+endToString();

    }
}

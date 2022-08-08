package com.example.b07_project;

public class EventTime {
    int month;
    int day;
    TimeSlot startTime;
    //30 minute time-slots used
    int duration;
    public EventTime(TimeSlot startTime, int duration,int day) {
        this.startTime=startTime;
        this.duration=duration;
        this.day=day;
    }
    public String endToString() {
        TimeSlot ets = new TimeSlot(startTime.slot_no+duration);
        return ets.toString();
    }
    @Override
    public String toString() {
        String [] days = {
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday",
                "Sunday"
        };
        return days[day]+" at "+startTime+"-"+endToString();

    }
}

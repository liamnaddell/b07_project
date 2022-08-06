package com.example.b07_project;

public class TimeSlot {
    int slot_no;
    public TimeSlot(int slot_no) {
        this.slot_no=slot_no;
        return;
    }
    @Override
    public String toString() {
        int mins = slot_no*30;
        String []endings = {
                "00",
                "30"
        };
        String hours = (mins/60)+"";
        if (hours.length() == 1) {
            return "0"+hours+":" + endings[(mins % 60)/30];
        } else {
            return hours+":"+endings[(mins % 60)/30];
        }
    }
}

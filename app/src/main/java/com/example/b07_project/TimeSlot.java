package com.example.b07_project;

import java.io.Serializable;

public class TimeSlot implements Serializable {
    public int slotNo;
    public TimeSlot(int slot_no) {
        this.slotNo=slot_no;
        return;
    }
    @Override
    public String toString() {
        int mins = slotNo*30;
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

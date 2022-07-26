package com.example.b07_project;

import java.io.Serializable;

public class Venue implements Serializable {
    public VenueType type;
    public String name;
    public String venueDescription;
    String venueid;

    public Venue() {
        return;
    }
    // create venue with standard id; use this constrctor to load existing venue from database
    public Venue(VenueType v, String name, String venue_description,String venueid) {
        this.type=v;
        this.name=name;
        this.venueDescription=venue_description;
        this.venueid = venueid;
    }

    public Venue(VenueType v, String name, String venue_description) {
        this.type=v;
        this.name=name;
        this.venueDescription=venue_description;
    }

    int get_capacity() {
        switch (type) {
            case BASKETBALL_COURT:
                return 12;
            case TENNIS_COURT:
                return 4;
            case FOOTBALL_FIELD:
                return 22;
            default:
                return -1;
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
}



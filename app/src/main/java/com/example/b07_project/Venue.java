package com.example.b07_project;

public class Venue {
    VenueType type;
    String venue_description;
    String name;
    int venueid;
    public Venue(VenueType v, String name, String venue_description,int venueid) {
        this.type=v;
        this.name=name;
        this.venue_description=venue_description;
        this.venueid = venueid;
    }
    int get_capacity() {
        switch (type) {
            case BASKETBALL_COURT:
                return 12;
            case TENNIS_COURT:
                return 4;
            default:
                return -1;
        }
    }
    String get_venue_name(){
        return this.name;
    }

    String get_venue_description(){
        return this.venue_description;
    }




}



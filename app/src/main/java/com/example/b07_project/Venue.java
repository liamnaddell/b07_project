package com.example.b07_project;

public class Venue {
    VenueType type;
    String venue_description;
    String name;
    int venueid;

    static Integer venue_count = 0;

    // create venue with standard id; use this constrctor to load existing venue from database
    public Venue(VenueType v, String name, String venue_description) {
        this.type=v;
        this.name=name;
        this.venue_description=venue_description;
        this.venueid = venue_count;
        venue_count++;
    }

    // create venue with custom id; use this constructor to create new venue only
    public Venue(VenueType v, String name, String venue_description, int venueid) {
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
            case FOOTBALL_FIELD:
                return 22;
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


    @Override
    public String toString() {
        return this.name;
    }


}



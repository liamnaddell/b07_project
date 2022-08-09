package com.example.b07_project;

import java.sql.Time;

public class DatabaseInstance {
    private static Database db = null;
    private DatabaseInstance() {

    }
    public static void set_instance(Database x) {
        if (db == null) {
            db=x;
        }
    }
    public static Database get_instance() {
        if (db == null) {
            db = new MockDB();
            db.add_user("admin", "admin", true);
            db.add_user("user", "user", false);
            // hard code in list until server is ready
            int v1 = db.add_venue(VenueType.TENNIS_COURT, "toronto", "toronto tennis court");
            int v2 = db.add_venue(VenueType.BASKETBALL_COURT, "1234 Military Trail", "toronto basketball court");
        }
        return db;
    }

}

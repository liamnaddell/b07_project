package com.example.b07_project;

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

            //VenueType Basketball = VenueType.BASKETBALL_COURT;
            //db.add_venue(Basketball,"UTSC","Nice");
            //db.add_venue(Basketball,"Minh's House","Next to the Thamse");
            db.add_user("admin", "admin", true);
            db.add_user("user", "user", false);
            // hard code in list until server is ready
            int v1 = db.add_venue(VenueType.TENNIS_COURT,"toronto","toronto tennis court");
            int v2 = db.add_venue(VenueType.BASKETBALL_COURT,"1234 Military Trail","toronto basketball court");

            db.add_event(v1,"tennis", "play tennis",
                    2, "5pm July 31", "10pm July 31");
            db.add_event(v1,"tennis alejandro", "play tennis w/ alejandro", 1, "5pm","6pm");
            db.add_event(v1, "random guy concert",
                    "concert of a random guy", 3, "1am Aug 21",
                    "10am Aug 21");
            db.add_event(v2, "weed smoking contest",
                    "smoke week and have fun", 4, "1am Dec 22",
                    "10am Dec 22");
            db.add_event(v2, "swallow kids event",
                    "swallow kids and try not to choke", 5, "1am Sept 22",
                    "10am Sept 22");
        }
        return db;
    }

}

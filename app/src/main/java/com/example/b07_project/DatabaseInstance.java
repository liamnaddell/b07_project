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
            db = new FirebaseDB();
        }
        return db;
    }

}

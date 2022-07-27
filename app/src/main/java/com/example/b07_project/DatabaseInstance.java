package com.example.b07_project;

public class DatabaseInstance {
    private static Database db = null;
    private DatabaseInstance() {

    }
    public static Database get_instance() {
        if (db == null) {
            db = new MockDB();
        }
        return db;
    }

}

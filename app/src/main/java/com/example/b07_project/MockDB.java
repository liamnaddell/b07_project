package com.example.b07_project;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class MockDB implements Database {
    ArrayList<String> users;
    ArrayList<Integer>  salts;
    ArrayList<String> passwords;
    public MockDB() {
        users = new ArrayList<String>();
        salts = new ArrayList<Integer>();
        passwords = new ArrayList<String>();
    }
    public String hash(String s) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("no algorithmlaoa");
            System.exit(100);
        }
        byte []bytes = s.getBytes(StandardCharsets.UTF_8);
        md.update(bytes);
        byte []digest = md.digest();
        return new String(digest);
    }
    public String hash_pw(String password, int salt) {
        return hash(password+salt);
    }
    public boolean check_pw(String user,String password) {
        if (users.contains(user)) {
            int index = users.indexOf(user);
            Integer salt = salts.get(index);
            String hashed = hash_pw(password,salt);
            if (hashed.equals(passwords.get(index))) {
                return true;
            }
        }
        return false;
    }
    public void add_user(String user, String password) {
        int salt = (int) Math.random()*100000;
        String hashed = hash_pw(password,salt);
        this.users.add(user);
        this.passwords.add(hashed);
        this.salts.add(salt);
        return;

    }

}


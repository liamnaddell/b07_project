package com.example.b07_project;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.b07_project.ui.main.SectionsPagerAdapter;
import com.example.b07_project.databinding.ActivityUserPageBinding;
import com.google.firebase.auth.FirebaseAuth;

public class UserPage extends AppCompatActivity {

    private ActivityUserPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUserPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
    }

    public void signout_handler(View view) {
        System.out.println("signout handler");
        //uses Firebase authentication cuz i can't seem to get it to work without since
        //current login page relies on Firebase i think
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }

    public void back_handler(View view) {
        System.out.println("back handler");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
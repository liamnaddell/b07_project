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
import android.widget.Button;

import com.example.b07_project.ui.main.SectionsPagerAdapter;
import com.example.b07_project.databinding.ActivityUserPageBinding;

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
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                System.out.println("Tab ID: "+tab.getPosition());
                int tabid = tab.getPosition();
                if (tabid == 2) {
                    System.out.println("I think I am in a Venues tab lol");
                    Button b = (Button) findViewById(R.id.add_venue_submit);
                    if (b != null) {
                        b.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                System.out.println("clicky click click clicky clik");
                            }
                        });
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                return;
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                return;
            }
        });
        tabs.setupWithViewPager(viewPager);

    }

    public void signout_handler(View view) {
        System.out.println("signout handler");
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }

    public void back_handler(View view) {
        System.out.println("back handler");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void venue_submit_handler(View view) {
        System.out.println("Vebenue SUbmbit handberl");
        return;
    }
}
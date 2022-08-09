package com.example.b07_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.b07_project.databinding.ActivityUserPageBinding;
import com.example.b07_project.ui.main.SectionsPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity{
    BottomNavigationView bottomNavView;

    private ActivityUserPageBinding binding;

    EventPage eventFragment = new EventPage();
    VenuePage venueFragment = new VenuePage();
    UserPage userFragment = new UserPage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavView = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_container, eventFragment).commit();

        //FirebaseDB db = new FirebaseDB();
        //System.out.println("USER IS ADMIN: "+db.find_user_by_name("admin2"));

        bottomNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item){
                switch (item.getItemId()) {
                    case R.id.events:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_container, eventFragment).commit();
                        return true;

                    case R.id.venues:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_container, venueFragment).commit();
                        return true;

                    case R.id.my_account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_container, userFragment).commit();
                        return true;
                }
                return false;
            }
        });

    }
}


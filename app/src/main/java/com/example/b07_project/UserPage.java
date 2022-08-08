package com.example.b07_project;

import android.content.Context;
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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

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
                            public void onClick(View buttonview) {
                                System.out.println("Add Venue clicked");
                                View v = (View) buttonview.getParent();
                                Spinner spinner_w = (Spinner) v.findViewById(R.id.venue_type_spinner);
                                EditText venue_name_w = v.findViewById(R.id.add_venue_name);
                                EditText venue_description_w = v.findViewById(R.id.add_venue_description);

                                long item_id = spinner_w.getSelectedItemId();

                                System.out.println("spinner item id: "+item_id);

                                String []xs = tab.parent.getResources().getStringArray(R.array.venue_types);

                                System.out.println("Spinner item selected: "+xs[(int) item_id]);

                                VenueType []vts = {VenueType.BASKETBALL_COURT,VenueType.TENNIS_COURT};
                                VenueType vt =  vts[(int) item_id];

                                System.out.println("Adding venue with type: "+vt);

                                String venue_name = venue_name_w.getText().toString().trim();
                                String venue_description = venue_description_w.getText().toString().trim();

                                if (venue_name.equals("") || venue_description.equals("")) {
                                    Toast.makeText(UserPage.this, "Not Added", Toast.LENGTH_SHORT).show();
                                } else {
                                    System.out.println("With name: \"" + venue_name+"\"");
                                    System.out.println("With description: \"" + venue_description+"\"");

                                    Database db = DatabaseInstance.get_instance();
                                    Venue venu = new Venue(vt,venue_name,venue_description,0);
                                    db.add_venue(venu);
                                    Toast.makeText(UserPage.this, "Venue Added", Toast.LENGTH_SHORT).show();
                                    tabs.getTabAt(0).select();
                                }
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
    public void addVenuesOnClick(View v) {
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

    public void venue_submit_handler(View view) {
        System.out.println("Vebenue SUbmbit handberl");
        return;
    }
}
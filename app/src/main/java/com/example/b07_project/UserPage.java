package com.example.b07_project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.b07_project.ui.main.SectionsPagerAdapter;
import com.example.b07_project.databinding.ActivityUserPageBinding;
import com.google.firebase.auth.FirebaseAuth;

public class UserPage extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_user_page, container, false);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("My Events"));
        tabLayout.addTab(tabLayout.newTab().setText("My Venues"));
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        viewPager.setAdapter(new SectionsPagerAdapter(getActivity(), getChildFragmentManager()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        signout_handler(view);
        return view;
    }
    public void addVenuesOnClick(View v) {
    }
    public void venue_submit_handler(View view) {
        System.out.println("Vebenue SUbmbit handberl");
        return;
    }

    public void signout_handler(View view) {
        Button btnLogOut = view.findViewById(R.id.sign_out_button);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            FirebaseAuth mAuth;
            @Override
            public void onClick(View v) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                Intent intent = new Intent(getActivity(), LoginPage.class);
                startActivity(intent);
            }
        });
    }

    /**
    public void back_handler(View view) {
        System.out.println("back handler");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

     */
}
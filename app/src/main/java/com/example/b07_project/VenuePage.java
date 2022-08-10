package com.example.b07_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.b07_project.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class VenuePage extends Fragment {

    ListView venueView;
    ArrayList<Venue> venues = new ArrayList<Venue>();
    VenueAdapter venueAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View current_view = inflater.inflate(R.layout.activity_venue_page, container, false);
        TabLayout tabLayout = (TabLayout) current_view.findViewById(R.id.venue_tabs);
        final ViewPager viewPager = (ViewPager) current_view.findViewById(R.id.view_pager);
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

        return current_view;
    }




}

package com.example.b07_project.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.b07_project.AvailableVenues;
import com.example.b07_project.Database;
import com.example.b07_project.DatabaseInstance;
import com.example.b07_project.R;
import com.example.b07_project.UserFragmentMyEvents;
import com.example.b07_project.AdminAddVenues;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public Database db = DatabaseInstance.get_instance();
    @StringRes
    private static final int[] TAB_TITLES = new int[]{ R.string.tab_text_2, R.string.tab_text_3};
    private final Context mContext;
    public static boolean wasset = false;
    static boolean isadmin = false;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (!wasset) {
            isadmin = db.logged_in().isAdmin;
            wasset = true;
        }
        Fragment fragment = null;
        if(isadmin){
            switch(position) {
                case 0:
                    fragment = new AvailableVenues();
                    break;
                case 1:
                    fragment = new AdminAddVenues();
                    break;
                }
        }
        else{
            switch(position) {
                case 0:
                    fragment = new AvailableVenues();
                    break;
            }
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        if (!wasset) {
            isadmin = db.logged_in().isAdmin;
            wasset = true;
        }
        if(isadmin){return 2;}
        return 1;
    }
}
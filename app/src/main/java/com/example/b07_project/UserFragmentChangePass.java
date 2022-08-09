package com.example.b07_project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UserFragmentChangePass extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_user_page_changepass, container, false);
        Button b = (Button) view.findViewById(R.id.add_venue_submit);
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

                    String []xs = getActivity().getResources().getStringArray(R.array.venue_types);

                    System.out.println("Spinner item selected: "+xs[(int) item_id]);

                    VenueType []vts = {VenueType.BASKETBALL_COURT,VenueType.TENNIS_COURT};
                    VenueType vt =  vts[(int) item_id];

                    System.out.println("Adding venue with type: "+vt);

                    String venue_name = venue_name_w.getText().toString().trim();
                    String venue_description = venue_description_w.getText().toString().trim();

                    if (venue_name.equals("") || venue_description.equals("")) {
                        Toast.makeText(getActivity(), "Not Added", Toast.LENGTH_SHORT).show();
                    } else {
                        System.out.println("With name: \"" + venue_name+"\"");
                        System.out.println("With description: \"" + venue_description+"\"");

                        Database db = DatabaseInstance.get_instance();
                        db.add_venue(vt, venue_name, venue_description);
                        Toast.makeText(getActivity(), "Venue Added", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        return view;

    }
}

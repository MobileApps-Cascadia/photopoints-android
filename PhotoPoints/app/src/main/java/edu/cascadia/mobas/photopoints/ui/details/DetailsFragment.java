package edu.cascadia.mobas.photopoints.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputEditText;

import edu.cascadia.mobas.photopoints.R;
import edu.cascadia.mobas.photopoints.model.Plant;

public class DetailsFragment extends Fragment {

    private DetailsViewModel model;
    private static String desc;
    private static String common;
    private static String species;


    //Plant information fragment.


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_details, container, false);

        model = new ViewModelProvider(this).get(DetailsViewModel.class);

        model.getDetails_Desc().setValue(desc);
        model.getDetails_Name().setValue(common);
        model.getDetails_Sci_Name().setValue(species);

        final TextView textView1 = root.findViewById(R.id.text_plantDescription);
        final TextView textView2 = root.findViewById(R.id.text_plantName);
        final TextView textView3 = root.findViewById(R.id.text_scientificName);
        final Observer<String> detailsObserver = new Observer<String>() {
        @Override
        public void onChanged(String s) {
            textView1.setText(s);
            }
        };
        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView2.setText(s);
            }
        };
        final Observer<String> sciObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView3.setText(s);
            }
        };

        model.getDetails_Desc().observe(getViewLifecycleOwner(),detailsObserver);
        model.getDetails_Name().observe(getViewLifecycleOwner(), nameObserver);
        model.getDetails_Sci_Name().observe(getViewLifecycleOwner(), sciObserver);




        root.findViewById(R.id.fab_uploadDetails).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });

        root.findViewById(R.id.fab_takePictureDetails).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });




        return root;

    }
    public static DetailsFragment setInstance(Bundle bundle) {
        desc = bundle.getString("desc");
        common = bundle.getString("common");
        species = bundle.getString("species");
        return new DetailsFragment();
    }
}

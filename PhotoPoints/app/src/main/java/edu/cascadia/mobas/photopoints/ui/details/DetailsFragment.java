package edu.cascadia.mobas.photopoints.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputEditText;

import edu.cascadia.mobas.photopoints.R;
import edu.cascadia.mobas.photopoints.model.Plant;

public class DetailsFragment extends Fragment {


    //Plant information fragment. to do: use plant object to dynamically change text so we don't have to do that all by hand like plebs

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_details, container, false);
        Button button = root.findViewById(R.id.fab_uploadDetails);
        Button button2 = root.findViewById(R.id.fab_takePictureDetails);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });




        return root;

    }

}

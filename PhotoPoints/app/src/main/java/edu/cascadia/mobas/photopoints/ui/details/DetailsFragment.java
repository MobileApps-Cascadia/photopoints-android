package edu.cascadia.mobas.photopoints.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputEditText;

import edu.cascadia.mobas.photopoints.R;
import edu.cascadia.mobas.photopoints.model.Plant;

public class DetailsFragment extends Fragment {
    String text1;
    String text2;

    //Plant information fragment. TODO: use plant object to dynamically change text so we don't have to do that all by hand like plebs

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_details, container, false);



        Plant test = new Plant(text1, text2){

        };


        return root;

    }

}

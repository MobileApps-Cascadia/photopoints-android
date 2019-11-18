package edu.cascadia.mobas.photopoints.ui.photopoints;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.cascadia.mobas.photopoints.R;

public class PhotoPointsFragment extends Fragment {

    private PhotoPointsViewModel mPhotoPointsViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mPhotoPointsViewModel =
                ViewModelProviders.of(this).get(PhotoPointsViewModel.class);

        View root = inflater.inflate(R.layout.fragment_scan, container, false);

        final TextView textView = root.findViewById(R.id.text_home);

        mPhotoPointsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }

}

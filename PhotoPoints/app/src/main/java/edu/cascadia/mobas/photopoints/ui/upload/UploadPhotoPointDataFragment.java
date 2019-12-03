package edu.cascadia.mobas.photopoints.ui.upload;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import edu.cascadia.mobas.photopoints.R;
import edu.cascadia.mobas.photopoints.model.PhotoPoint;

public class UploadPhotoPointDataFragment extends Fragment {

    //Used to retrieve data from bundle
    private static final String PHOTOPOINT_TYPE = "PhotoPointType";
    private static final String PHOTOPOINT_ID = "PhotoPointID";

    //Used to store data from bundle
    private PhotoPoint.PhotoPointType mPhotoPointType;
    private int mPhotoPointID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload_photo_point_data, container, false);

        if (savedInstanceState != null){
            Bundle args = getArguments();
            mPhotoPointID = args.getInt(PHOTOPOINT_ID);
            mPhotoPointType = PhotoPoint.PhotoPointType.values()[args.getInt(PHOTOPOINT_TYPE)];
        }

        //TODO: Check for camera permissions.

        view.findViewById(R.id.fab_takePicture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        view.findViewById(R.id.fab_uploadData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadData();
            }
        });

        return view;
    }

    private void takePicture(){
        //TODO: Implement method.
    }

    private void uploadData(){
        //TODO: Implement method.
    }
}

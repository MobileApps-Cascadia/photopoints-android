package edu.cascadia.mobas.photopoints.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import edu.cascadia.mobas.photopoints.R;
import edu.cascadia.mobas.photopoints.model.PhotoPoint;

public class DetailsFragment extends Fragment {

    //Key to get the argument values for item id.
    public static final String ITEM_ID = "ItemID";
    public static final String PHOTOPOINT_TYPE = "ItemType";

    private int mItemID;
    private PhotoPoint.PhotoPointType mPhotoPointType;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_details, container, false);

        Bundle args = getArguments();
        if(args != null){
            mItemID = args.getInt(ITEM_ID);
            mPhotoPointType = PhotoPoint.PhotoPointType.values()[args.getInt(PHOTOPOINT_TYPE)];
        }

        return root;
    }
}

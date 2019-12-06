package edu.cascadia.mobas.photopoints.ui.upload;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.ref.WeakReference;
import edu.cascadia.mobas.photopoints.R;
import edu.cascadia.mobas.photopoints.model.PhotoPoint;
import edu.cascadia.mobas.photopoints.repo.PhotoPointsRepository;
import edu.cascadia.mobas.photopoints.ui.details.DetailsFragment;

/*This fragment is used to capture the plant data and pictures.*/
public class UploadPhotoPointDataFragment extends Fragment {

    //Used to retrieve data from bundle
    public static final String PHOTOPOINT_ID = "PhotoPointID";

    //Used to store data from or for the bundle
    private int mPhotoPointID;
    private int mItemID;
    private int mPhotoPointType;

    private TextView mText_name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload_photo_point_data, container, false);

        Bundle args = getArguments();
        if(args != null){
            mPhotoPointID = args.getInt(PHOTOPOINT_ID);
        }

        mText_name = view.findViewById(R.id.text_name);

        if(mPhotoPointID != 0){
            //Get the PhotoPoint information
            new GetPhotoPointInfoAsync(this).execute(mPhotoPointID);
        }

        view.findViewById(R.id.button_details).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreDetails(v);
            }
        });

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

    private void moreDetails(View v){
        //In the unlikely event that SQL was slower than the user, we will show a toast to the user, prompting them to click again.
        if(mItemID == 0){
            Toast.makeText(v.getContext(), getString(R.string.try_again), Toast.LENGTH_SHORT).show();
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putInt(DetailsFragment.ITEM_ID, mItemID);
        bundle.putInt(DetailsFragment.PHOTOPOINT_TYPE, mPhotoPointType);

        Navigation.findNavController(v)
                .navigate(R.id.action_navigation_uploadPhotoPointData_to_navigation_photoPoint_details, bundle);
    }

    private void takePicture(){
        //TODO: Implement method.
    }

    private void uploadData(){
        //TODO: Implement method.
    }

    private void updateUI(PhotoPoint point){
        mText_name.setText(point.getQRCode());
        mItemID = point.getItemID();
        mPhotoPointType = point.getPhotoPointType().ordinal();
    }

    /*Gets the photopoint information asynchronously.*/
    private static class GetPhotoPointInfoAsync extends AsyncTask<Integer, Void, PhotoPoint>{

        private WeakReference<UploadPhotoPointDataFragment> mFragment;

        public GetPhotoPointInfoAsync(UploadPhotoPointDataFragment fragment) {
            this.mFragment = new WeakReference<>(fragment);
        }

        @Override
        protected void onPostExecute(PhotoPoint photoPoint) {
            super.onPostExecute(photoPoint);

            //Should really not happen because verified the ID in the previous screen.
            //But we can never be too sure...
            if(photoPoint == null){
                return;
            }

            mFragment.get().updateUI(photoPoint);
        }

        @Override
        protected PhotoPoint doInBackground(Integer... integers) {
            return new PhotoPointsRepository(mFragment.get().getContext()).getById(integers[0]);
        }
    }
}

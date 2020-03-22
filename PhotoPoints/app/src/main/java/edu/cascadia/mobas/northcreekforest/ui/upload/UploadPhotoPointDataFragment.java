package edu.cascadia.mobas.northcreekforest.ui.upload;


import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import edu.cascadia.mobas.northcreekforest.R;
import edu.cascadia.mobas.northcreekforest.model.PointItem;
import edu.cascadia.mobas.northcreekforest.repo.PointItemRepository;

/*This fragment is used to capture the plant data and pictures.*/
public class UploadPhotoPointDataFragment extends Fragment {

    //Used to retrieve data from bundle
    public static final String PHOTOPOINT_ID = "PhotoPointID";

    //Used to store data from bundle
    private int mPhotoPointID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload_photo_point_data, container, false);

        Bundle args = getArguments();
        if(args != null){
            mPhotoPointID = args.getInt(PHOTOPOINT_ID);
        }

        if(mPhotoPointID != 0){
            //Get the PointItem information
            new GetPhotoPointInfoAsync(this, (TextView)view.findViewById(R.id.text_name)).execute(mPhotoPointID);
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

    /*Gets the photopoint information asynchronously.*/
    private static class GetPhotoPointInfoAsync extends AsyncTask<Integer, Void, PointItem>{

        private WeakReference<Fragment> mFragment;
        private WeakReference<TextView> mPhotoPointName;

        public GetPhotoPointInfoAsync(Fragment fragment, TextView photoPointName) {
            this.mFragment = new WeakReference<>(fragment);
            this.mPhotoPointName = new WeakReference<>(photoPointName);
        }

        @Override
        protected void onPostExecute(PointItem photoPoint) {
            super.onPostExecute(photoPoint);

            //Should really not happen because verified the ID in the previous screen.
            //But we can never be too sure...
            if(photoPoint == null){
                return;
            }

            mPhotoPointName.get().setText(photoPoint.getQRCode());
        }

        @Override
        protected PointItem doInBackground(Integer... integers) {
            return new PointItemRepository(mFragment.get().getContext()).getById(integers[0]);
        }
    }
}

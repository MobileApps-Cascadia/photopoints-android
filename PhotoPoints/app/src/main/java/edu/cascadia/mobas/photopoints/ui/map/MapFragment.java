package edu.cascadia.mobas.photopoints.ui.map;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.lang.ref.WeakReference;
import java.util.List;
import edu.cascadia.mobas.photopoints.R;
import edu.cascadia.mobas.photopoints.model.Path;
import edu.cascadia.mobas.photopoints.model.PointItem;
import edu.cascadia.mobas.photopoints.repo.PathsRepository;
import edu.cascadia.mobas.photopoints.repo.PointItemRepository;

public class MapFragment extends Fragment {

    PointItemRepository repoPhotoPoints;
    PathsRepository repoPaths = new PathsRepository();

    private String TAG = "PHOTOPOINTS_MAP";
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private boolean mLocationPermissionGranted;
    private Location mLastKnownLocation;
    private GoogleMap map;

    //Used for location services
    public static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_map, container, false);
        repoPhotoPoints = new PointItemRepository(getContext());

        getLocationPermission();

        SupportMapFragment frag = (SupportMapFragment)getFragmentManager()
                                    //get the current active fragment
                                    .getPrimaryNavigationFragment()
                                    //gets the children of the current active fragment
                                    .getChildFragmentManager()
                                    //gets the photopoint fragment.
                                    .findFragmentById(R.id.map_photoPoints);


        frag.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                //Sanity check
                if(googleMap== null){
                    return;
                }

                map = googleMap;
                map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                updateLocationUI(map);
                setMapMarkersAndPaths(map);
                //Example of how the task is called.
                new PhotoPointAsyncTask(getFragmentManager().getPrimaryNavigationFragment(), map).execute();
            }
        });

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());

        return root;
    }

    /*
    * Updates the map ui with the controls to zoom in on the user if the user has given their permission.
    */
    private void updateLocationUI(GoogleMap map){

        try {

            if (mLocationPermissionGranted) {
                map.setMyLocationEnabled(true);
                map.getUiSettings().setMyLocationButtonEnabled(true);
                map.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
                    @Override
                    public boolean onMyLocationButtonClick() {
                        getDeviceLocation();
                        return false;
                    }
                });
            } else {
                map.setMyLocationEnabled(false);
                map.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }
        }
        catch(Exception ex){
            Log.d(TAG, ex.getMessage());
        }
    }

    /*
    * Gets the device location if the users allows it.
     */
    private void getDeviceLocation(){
        if (!mLocationPermissionGranted) {
            return;
        }

        try {
            Task locationResult = mFusedLocationProviderClient.getLastLocation();
            locationResult.addOnCompleteListener(new OnCompleteListener() {

                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        // Set the map's camera position to the current location of the device.
                        mLastKnownLocation = (Location)task.getResult();
                    }
                }
            });
        }
        catch(Exception ex){
            Log.d(TAG, ex.getMessage());
        }
    }



    /*
    *Sets the markers for the photopoints and the paths through the forest.
    *In the end, it zooms in on the North Creek Forest.
     */
    private void setMapMarkersAndPaths(GoogleMap map){

        try {
            // Draw all the paths on the map
            List<Path> paths = repoPaths.getAll();
            for (Path p : paths) {
                map.addPolyline(p.getPolylineOptions(getContext()));
            }
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(47.77509422, -122.19170793), 18.8f));
        }
        catch(Exception ex){
            Log.d(TAG, ex.getMessage());
        }
    }

    /*
    * asks the user for user-location permission if these are not set yet and sets the mLocationPermissionGranted variable.
    */
    private void getLocationPermission() {

        if (ContextCompat.checkSelfPermission(getContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            mLocationPermissionGranted = true;

        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
    }

    //Example for how the AsyncTask should be implemented.
    public static class PhotoPointAsyncTask extends AsyncTask<PointItem, Void, List<PointItem>>{

        private WeakReference<Fragment> mFragment;
        private WeakReference<GoogleMap> mMap;
        private PointItemRepository mRepo;


        public PhotoPointAsyncTask(Fragment frag, GoogleMap map){
            mFragment = new WeakReference<>(frag);
            mMap = new WeakReference<>(map);
            mRepo = new PointItemRepository(mFragment.get().getContext());
        }

        @Override
        protected void onPostExecute(List<PointItem> photoPoints) {
            super.onPostExecute(photoPoints);

            //Create options with dot icon.
            MarkerOptions options = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.dot));

            // Place markers for all the PhotoPoints on the map
            for(PointItem point : photoPoints){
                mMap.get().addMarker(options.position(point.getLatLng()));
            }
        }

        @Override
        protected List<PointItem> doInBackground(PointItem... PhotoPoint) {
            return mRepo.getAll();
        }
    }
}
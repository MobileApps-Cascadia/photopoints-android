package edu.cascadia.mobas.photopoints.ui.map;

import android.content.pm.PackageManager;
import android.location.Location;
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
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import edu.cascadia.mobas.photopoints.R;
import edu.cascadia.mobas.photopoints.model.PhotoPoint;
import edu.cascadia.mobas.photopoints.repo.PhotoPointsRepository;

public class MapFragment extends Fragment {

    PhotoPointsRepository repo = new PhotoPointsRepository();
    private String TAG = "PHOTOPOINTS_MAP";
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private boolean mLocationPermissionGranted;
    private Location mLastKnownLocation;

    //Used for location services
    public static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_map, container, false);

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

                updateLocationUI(googleMap);
                setMapMarkersAndPaths(googleMap);
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
            //Create options with dot icon.
            MarkerOptions options = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.dot));

            //The line that is drawn between points to create a "path"
            //Store the latlongs of the points
            LatLng[] latLongs = new LatLng[repo.count()];

            int counter = 0;
            for(PhotoPoint point : repo.getAll()){
                latLongs[counter] = point.getLatLng();
                counter++;
            }

            //Create the options with these latlons.
            PolylineOptions lineOptions = new PolylineOptions()
                    .clickable(false)
                    .add(latLongs)
                    //Set the color to beige.
                    .color(ContextCompat.getColor(getContext(), R.color.mapsPath));

            //Add the poly lines.
            map.addPolyline(lineOptions);

            //map these latlons
            for(int i = 0; i < latLongs.length; i++){
                map.addMarker(options.position(latLongs[i]));
            }

            //Add an additional marker to connect the final point to the second point in the array to create a circle.
            map.addMarker(options.position(latLongs[1]));

            //Zoom in on area.
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLongs[0], 15.8f));
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
}

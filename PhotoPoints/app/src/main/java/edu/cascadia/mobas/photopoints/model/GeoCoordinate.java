package edu.cascadia.mobas.photopoints.model;

import androidx.annotation.NonNull;
import com.google.android.gms.maps.model.LatLng;



public class GeoCoordinate {
    private static double mMinLatitude = -90;
    private static double mMaxLatitude = 90;
    private static double mMinLongitude = -180.0;
    private static double mMaxLongitude = 180.0;

    private double mLatitude = 0;
    private double mLongitude = 0;
    private boolean mIsValid = false;


    // constructors

    public GeoCoordinate(double lat, double lng) {
        if (GeoCoordinate.mMinLatitude <= lat && lat <= GeoCoordinate.mMaxLatitude
                && GeoCoordinate.mMinLongitude <= lng && lng <= mMaxLongitude) {
            mLatitude = lat;
            mLongitude = lng;
            mIsValid = true;
        }
    }

    // constructor:  create from string

    public GeoCoordinate(@NonNull String coords) {
        String [] coordSet = coords.trim().split(",", 4);
        double lng = Double.parseDouble(coordSet[0]);
        double lat = Double.parseDouble(coordSet[1]);
        // swap coordinates if order given is longitude,latitude
        if (lat < 0 && lng > 0) {
            double temp = lat;
            lat = lng;
            lng = temp;
        }

        if (GeoCoordinate.mMinLatitude <= lat && lat <= GeoCoordinate.mMaxLatitude
                && GeoCoordinate.mMinLongitude <= lng && lng <= mMaxLongitude) {
            mLatitude = lat;
            mLongitude = lng;
            mIsValid = true;
        }
    }


    // return the latitude

    public double latitude() {
        return mLatitude;
    }


    // return the longitude

    public double longitude() {
        return mLongitude;
    }

    // return the coordinate as a Maps API LatLng

    public LatLng getLatLng() {
        return new LatLng(mLatitude, mLongitude);
    }


    // return true if coordinate is valid and in bounds
    public boolean isValid() {
        return mIsValid;
        }


    // set the bounding box which determine valid range of lat/lng coordinates

    public static void setBounds(double minLat, double minLng, double maxLat, double maxLng) {
        GeoCoordinate.mMinLatitude = minLat;
        GeoCoordinate.mMinLongitude = minLng;
        GeoCoordinate.mMaxLatitude = maxLat;
        GeoCoordinate.mMaxLongitude = maxLng;
    }

}

package edu.cascadia.mobas.photopoints.model;

import androidx.annotation.NonNull;
import com.google.android.gms.maps.model.LatLng;



public class GeoCoordinate {
    private static final double mMinLatitude = -90;
    private static final double mMaxLatitude = 90;
    private static final double mMinLongitude = -180.0;
    private static final double mMaxLongitude = 180.0;
    private static final double mMinAltitude = 0;

    private double mLongitude = 0;
    private double mLatitude = 0;
    private double mAltitude = 0;
    private boolean mIsValid = false;


    // constructors

    public GeoCoordinate(double lat, double lng, double alt) {
        if (GeoCoordinate.mMinLatitude <= lat && lat <= GeoCoordinate.mMaxLatitude
                && GeoCoordinate.mMinLongitude <= lng && lng <= mMaxLongitude) {
            mLatitude = lat;
            mLongitude = lng;
            mAltitude = alt;
            mIsValid = true;
        }
    }

    public GeoCoordinate(double lat, double lng) {
        this(lat, lng, 0);
    }

    // constructor:  create from string

    public GeoCoordinate(@NonNull String coords) {
        String [] coordSet = coords.trim().split(",", 4);
        double lng = Double.parseDouble(coordSet[0]);
        double lat = Double.parseDouble(coordSet[1]);
        double alt = (coordSet.length < 3 ? 0 : Double.parseDouble(coordSet[2]));

        // swap coordinates if order given is longitude,latitude (based on North America)
        if (lat < 0 && lng > 0) {
            double temp = lat;
            lat = lng;
            lng = temp;
        }

        if (GeoCoordinate.mMinLatitude <= lat && lat <= GeoCoordinate.mMaxLatitude
                && GeoCoordinate.mMinLongitude <= lng && lng <= mMaxLongitude) {
            mLatitude = lat;
            mLongitude = lng;
            mAltitude = alt;
            mIsValid = true;
        }
    }

    // return the latitude
    public double getLatitude() {
        return mLatitude;
    }


    // return the longitude
    public double getLongitude() {
        return mLongitude;
    }


    // return the altitude
    public double getAltitude() { return mAltitude; }


    // return the coordinate as a Maps API LatLng
    public LatLng getLatLng() {
        return new LatLng(mLatitude, mLongitude);
    }

    // set the latitude
    public void setLatitude(long lat) { this.mLatitude = lat; }

    // set the longitude
    public void setLongitude(long lng) { this.mLongitude = lng; }

    // set the altitude
    public void setAltutude(long alt) { this.mAltitude = alt; }

}

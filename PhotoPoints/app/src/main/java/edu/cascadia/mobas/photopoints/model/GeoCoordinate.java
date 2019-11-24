package edu.cascadia.mobas.photopoints.model;

import com.google.android.gms.maps.model.LatLng;

import org.jetbrains.annotations.NotNull;


public class GeoCoordinate {

    private static double mMinLatitude = -180.0;
    private static double mMaxLatitude = 180.0;
    private static double mMinLongitude = -180.0;
    private static double mMaxLongitude = 180.0;

    private double mLatitude = 0;
    private double mLongitude = 0;
    private boolean mIsValid = false;

    // Allows a bounding box to determine valid range of lat/lng coordinates
    public static void setBounds(double minLat, double minLng, double maxLat, double maxLng) {
        GeoCoordinate.mMinLatitude = minLat;
        GeoCoordinate.mMinLongitude = minLng;
        GeoCoordinate.mMaxLatitude = maxLat;
        GeoCoordinate.mMaxLongitude = maxLng;
    }


    public GeoCoordinate(double lat, double lng) {
        if (GeoCoordinate.mMinLatitude <= lat && lat <= GeoCoordinate.mMaxLatitude
                && GeoCoordinate.mMinLongitude <= lng && lng <= mMaxLongitude) {
            mLatitude = lat;
            mLongitude = lng;
            mIsValid = true;
        }
    }

    // Create from KML Coordinates String:  Longitude,Latitude,Altitude
    public GeoCoordinate(@NotNull String coords) {
        String [] coordSet = coords.trim().split(",", 3);
        double lng = Double.parseDouble(coordSet[0]);
        double lat = Double.parseDouble(coordSet[1]);
        if (GeoCoordinate.mMinLatitude <= lat && lat <= GeoCoordinate.mMaxLatitude
                && GeoCoordinate.mMinLongitude <= lng && lng <= mMaxLongitude) {
            mLatitude = lat;
            mLongitude = lng;
            mIsValid = true;
        }
    }

    public double getLatitude() {
        return mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public LatLng getLatLng() {
        return new LatLng(mLatitude, mLongitude);
    }

    public boolean isValid() {
        return mIsValid;
    }
}

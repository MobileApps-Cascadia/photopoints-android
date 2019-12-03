package edu.cascadia.mobas.photopoints.model;

import com.google.android.gms.maps.model.LatLng;

public class PhotoPoint {

    public enum PhotoPointType{
        Creek, //0
        Plant  //1
    }

    private String mPhotoPointID;
    private Double mLatitude;
    private Double mLongitude;
    private PhotoPointType mPhotoPointType;

    public PhotoPoint(String photoPointID, Double latitude, Double longitude){
        this.mPhotoPointID = photoPointID;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
    }

    public PhotoPoint(String photoPointID, Double latitude, Double longitude, PhotoPointType photoPointType) {
        this(photoPointID, latitude, longitude);
        this.mPhotoPointType = photoPointType;
    }

    public PhotoPoint(String photoPointID, Double latitude, Double longitude, int photoPointType) {
        this(photoPointID, latitude, longitude);
        this.mPhotoPointType = PhotoPointType.values()[photoPointType];
    }

    public LatLng getLatLng(){
        return new LatLng(mLatitude, mLongitude);
    }

}

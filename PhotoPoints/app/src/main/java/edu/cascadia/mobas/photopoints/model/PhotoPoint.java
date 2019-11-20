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

    public PhotoPoint(String photoPointID, Double latitude, Double longitude, PhotoPointType mPhotoPointType) {
        this.mPhotoPointID = photoPointID;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
        this.mPhotoPointType = mPhotoPointType;
    }

    public String getPhotoPointID() {
        return mPhotoPointID;
    }

    public void setPhotoPointID(String photoPointID) {
        this.mPhotoPointID = photoPointID;
    }

    public Double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(Double latitude) {
        this.mLatitude = latitude;
    }

    public Double getLongitude() {
        return mLongitude;
    }

    public void setmLongitude(Double longitude) {
        this.mLongitude = longitude;
    }

    public PhotoPointType getPhotoPointType() {
        return mPhotoPointType;
    }

    public void setPhotoPointType(PhotoPointType photoPointType) {
        this.mPhotoPointType = photoPointType;
    }

    public LatLng getLatLng(){
        return new LatLng(mLatitude, mLongitude);
    }
}

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
    private String mQRCode;

    public PhotoPoint(String photoPointID, Double latitude, Double longitude){
        this.mPhotoPointID = photoPointID;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
    }

    public PhotoPoint(String photoPointID, Double latitude, Double longitude, PhotoPointType mPhotoPointType, String qrCode) {
        this(photoPointID, latitude, longitude, mPhotoPointType);
        mQRCode = qrCode;
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

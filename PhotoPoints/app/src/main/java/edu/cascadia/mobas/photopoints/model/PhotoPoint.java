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

    public PhotoPoint(String photoPointID, Double latitude, Double longitude, PhotoPointType mPhotoPointType) {
        this.mPhotoPointID = photoPointID;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
        this.mPhotoPointType = mPhotoPointType;
    }

    public PhotoPoint(String photoPointID, Double latitude, Double longitude, PhotoPointType mPhotoPointType, String qrCode) {
        this(photoPointID, latitude, longitude, mPhotoPointType);
        mQRCode = qrCode;
    }

    //<editor-fold desc="Getters and setters">
    
    public String getPhotoPointID() {
        return mPhotoPointID;
    }

    public void setmPhotoPointID(String mPhotoPointID) {
        this.mPhotoPointID = mPhotoPointID;
    }

    public Double getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(Double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public Double getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(Double mLongitude) {
        this.mLongitude = mLongitude;
    }

    public PhotoPointType getmPhotoPointType() {
        return mPhotoPointType;
    }

    public void setmPhotoPointType(PhotoPointType mPhotoPointType) {
        this.mPhotoPointType = mPhotoPointType;
    }

    public String getmQRCode() {
        return mQRCode;
    }

    public void setmQRCode(String mQRCode) {
        this.mQRCode = mQRCode;
    }

    public LatLng getLatLng(){
        return new LatLng(mLatitude, mLongitude);
    }

    //</editor-fold>
}

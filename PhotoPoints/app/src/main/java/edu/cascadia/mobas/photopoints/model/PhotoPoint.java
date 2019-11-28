package edu.cascadia.mobas.photopoints.model;

import com.google.android.gms.maps.model.LatLng;

public class PhotoPoint {

    public enum PhotoPointType{
        Creek, //0
        Plant  //1
    }

    private int mPhotoPointID;
    private Double mLatitude;
    private Double mLongitude;
    private PhotoPointType mPhotoPointType;
    private String mQRCode;

    public PhotoPoint(int photoPointID, Double latitude, Double longitude){
        this.mPhotoPointID = photoPointID;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
    }

    public PhotoPoint(int photoPointID, Double latitude, Double longitude, String qrCode){
        this(photoPointID, latitude, longitude);
        this.mQRCode = qrCode;
    }

    public PhotoPoint(int photoPointID, Double latitude, Double longitude, String qrCode, PhotoPointType mPhotoPointType) {
        this(photoPointID, latitude, longitude, qrCode);
        this.mPhotoPointType = mPhotoPointType;
    }

    public PhotoPoint(int photoPointID, Double latitude, Double longitude, String qrCode, int photoPointType) {
        this(photoPointID, latitude, longitude, qrCode);
        this.mPhotoPointType = PhotoPointType.values()[photoPointType];
    }

    //<editor-fold desc="Getters and setters">

    public int getPhotoPointID() {
        return mPhotoPointID;
    }

    public void setPhotoPointID(int photoPointID) {
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

    public void setLongitude(Double longitude) {
        this.mLongitude = longitude;
    }

    public PhotoPointType getPhotoPointType() {
        return mPhotoPointType;
    }

    public void setPhotoPointType(PhotoPointType photoPointType) {
        this.mPhotoPointType = photoPointType;
    }

    public String getQRCode() {
        return mQRCode;
    }

    public void setQRCode(String QRCode) {
        this.mQRCode = QRCode;
    }

    public LatLng getLatLng(){
        return new LatLng(mLatitude, mLongitude);
    }

    //</editor-fold>
}

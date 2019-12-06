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
    private int mItemID;      // ID of item (plant, creek) this PhotoPoint refers to


    // provided for compatibility with existing data model
    public PhotoPoint(int photoPointID, Double latitude, Double longitude, String qrCode, int photoPointType) {
        this(photoPointID, latitude, longitude, qrCode, photoPointType, 1);
    }

    public PhotoPoint(int photoPointID, Double latitude, Double longitude, String qrCode, PhotoPointType photoPointType, int itemID) {
        this.mPhotoPointID = photoPointID;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
        this.mQRCode = qrCode;
        this.mPhotoPointType = photoPointType;
        this.mItemID = itemID;
    }

    public PhotoPoint(int photoPointID, Double latitude, Double longitude, String qrCode, int photoPointType, int itemID) {
        this(photoPointID, latitude, longitude, qrCode, PhotoPointType.values()[photoPointType], itemID);
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

    public int getItemID() {
        return mItemID;
    }

    //</editor-fold>
}

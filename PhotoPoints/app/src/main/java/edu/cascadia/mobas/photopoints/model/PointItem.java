package edu.cascadia.mobas.photopoints.model;

import com.google.android.gms.maps.model.LatLng;

public class PointItem {

    // TODO:  Make these final
    private int mId;
    private ItemType mItemType;
    private GeoCoordinate mLocation;
    private String mQRCode;
    private boolean mInactive;

    // TODO remove after rest of app is updated to use Geocoordinate
    private Double mLatitude;
    private Double mLongitude;
/*
    //TODO:  Remove this constructor after all uses are updated to new version
    public PointItem(int photoPointID, Double latitude, Double longitude, String qrCode, ItemType itemType, int itemID) {
        this.mId = photoPointID;
        this.mItemType = itemType;
        this.mLatitude = latitude;    // TODO:  use location
        this.mLongitude = longitude;  // TODO:  use location
        this.mQRCode = (qrCode.trim().equals("") ? null : qrCode.trim());
        this.mLocation = new GeoCoordinate(latitude, longitude);
        this.mInactive = false;
    }
*/
    public PointItem(int id, ItemType type, GeoCoordinate location, String qrCode, boolean inactive) {
        this.mId = id;
        this.mItemType = type;
        this.mLocation = location;
        this.mLatitude = location.latitude();   // TODO: use location
        this.mLongitude = location.longitude();  // TODO: use location
        this.mQRCode = (qrCode.trim().equals("") ? null : qrCode.trim());
        this.mInactive = inactive;
    }

    public int getId() { return mId; }
    public String getQRCode() { return mQRCode; }
    public GeoCoordinate getLocation() { return this.mLocation; }
    public ItemType getItemType() { return mItemType; }
    public Double getLatitude() { return mLocation.latitude(); }
    public Double getLongitude() { return mLocation.longitude(); }
    public LatLng getLatLng(){ return mLocation.getLatLng(); }

    public boolean isPhotoPoint() { return (mQRCode == null || mQRCode.trim().equals("")); }
    public boolean isInactive() { return this.mInactive; }
    public boolean isActive() { return !this.mInactive; }




    /*
    public PointItem(int photoPointID, Double latitude, Double longitude, String qrCode, int photoPointType, int itemID) {
        this(photoPointID, latitude, longitude, qrCode, ItemType.values()[photoPointType], itemID);
    }
*/







    // TODO:  Find references and change them to getId()
    public int getItemID() {
        return mId;
    }


    // TODO:  Find out if setters are necessary as instantiated object fields shouldn't be mutable
    public void setId(int photoPointID) {
        this.mId = photoPointID;
    }

    public void setLatitude(Double latitude) {
        this.mLatitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.mLongitude = longitude;
    }






    public void setPhotoPointType(ItemType itemType) {
        this.mItemType = itemType;
    }

    public void setQRCode(String QRCode) {
        this.mQRCode = QRCode;
    }


    //</editor-fold>
}

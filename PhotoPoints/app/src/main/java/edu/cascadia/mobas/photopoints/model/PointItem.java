package edu.cascadia.mobas.photopoints.model;

import com.google.android.gms.maps.model.LatLng;

public class PointItem {

    // TODO:  Make these final
    private int mId;
    private ItemTypeEnum mItemTypeEnum;
    private Coordinates mLocation;
    private String mQRCode;
    private boolean mInactive;

    public PointItem(int id, ItemTypeEnum type, Coordinates location, String qrCode, boolean inactive) {
        this.mId = id;
        this.mItemTypeEnum = type;
        this.mLocation = location;
        this.mQRCode = (qrCode.trim().equals("") ? null : qrCode.trim());
        this.mInactive = inactive;
    }

    public int getId() { return mId; }
    public String getQRCode() { return mQRCode; }
    public Coordinates getLocation() { return this.mLocation; }
    public ItemTypeEnum getItemType() { return mItemTypeEnum; }
    public double getLatitude() { return mLocation.getLatitude(); }
    public double getLongitude() { return mLocation.getLongitude(); }
    public double getAltitude() { return mLocation.getAltitude(); }
    public LatLng getLatLng(){ return new LatLng(mLocation.getLatitude(), mLocation.getLongitude()); }

    public boolean isPhotoPoint() { return (mQRCode == null || mQRCode.trim().equals("")); }
    public boolean isInactive() { return this.mInactive; }
    public boolean isActive() { return !this.mInactive; }



    // TODO:  Find references and change them to getId()
    public int getItemID() {
        return mId;
    }

}

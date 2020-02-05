package edu.cascadia.mobas.photopoints.model;

import android.graphics.Point;

import com.google.android.gms.maps.model.LatLng;

public class PointImage {

    public enum ImageOrientation {
        Landscape,
        Portrait,
        Square,
        Unknown
    }

    private int mPointImageID;
    private String mURI;
    private ImageOrientation mImageOrientation;
    private Double mHeight;
    private Double mWidth;
    private String mSource;
    private int mItemID; // ID of item (plant, creek) this is object references to an image of


    public PointImage(int pointImageID, String URI, Double height, Double width, ImageOrientation imageOrientation, int itemID) {
        this.mPointImageID = pointImageID;
        this.mURI = URI;
        this.mHeight = height;
        this.mWidth = width;
        this.mImageOrientation = imageOrientation;
        this.mItemID = itemID;
    }

    public int getPointImageID() {
        return mPointImageID;
    }
    public String getURI() {
        return mURI;
    }
    public ImageOrientation getImageOrientation() {
        return mImageOrientation;
    }
    public Double getHeight() { return mHeight; }
    public Double getWidth() { return mWidth; }
    public String getSource() { return mSource; }
    public int getItemID() {
        return mItemID;
    }

    //TODO: add setters if necessary

}

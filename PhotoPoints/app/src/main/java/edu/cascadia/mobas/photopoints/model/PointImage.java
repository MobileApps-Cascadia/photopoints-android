package edu.cascadia.mobas.photopoints.model;

import android.graphics.Point;

import com.google.android.gms.maps.model.LatLng;

public class PointImage {

    public enum ImageOrientation {
        landscape,
        portrait,
        square
    }

    private int mPointImageID;
    private String mURI;
    private ImageOrientation mImageOrientation;
    private Double mHeight;
    private Double mWidth;
    private String mSource;
    private int mItemID; // ID of item (plant, creek) this is object references to an image of

    // provided for compatibility with existing data model
    public PointImage(int pointImageID, String URI, Double height, Double width, String source, int imageOrientation) {
        this(pointImageID, URI, height, width, source, imageOrientation, 1);
    }

    public PointImage(int pointImageID, String URI, Double height, Double width, ImageOrientation imageOrientation, int itemID) {
        this.mPointImageID = pointImageID;
        this.mURI = URI;
        this.mHeight = height;
        this.mWidth = width;
        this.mImageOrientation = imageOrientation;
        this.mItemID = itemID;
    }

    public PointImage(int pointImageID, String URI, Double height, Double width, String source, int imageOrientation, int itemID) {
        this(pointImageID, URI, height, width, source, ImageOrientation.values()[imageOrientation], itemID);
    }

    public int getPointImageID() {
        return mPointImageID;
    }

    public void setPointImageID(int pointImageID) {
        this.mPointImageID = pointImageID;
    }

    public ImageOrientation getImageOrientation() {
        return mImageOrientation;
    }

    public void setImageOrientation(PointImage.ImageOrientation imageOrientation) {
        this.mImageOrientation = imageOrientation;
    }

    public String getURI() {
        return mURI;
    }

    public void setURI(String URI) {
        this.mURI = URI;
    }

    public int getItemID() {
        return mItemID;
    }

}

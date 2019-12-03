package edu.cascadia.mobas.photopoints.data.dto;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "PhotoPoints")
public class DBPhotoPoint {

    @PrimaryKey ()
    @NonNull
    @ColumnInfo(name ="PhotoPointID")
    private String PhotoPointID;

    @ColumnInfo(name ="Latitude")
    private Double Latitude;

    @ColumnInfo(name ="Longitude")
    private Double Longitude;

    @ColumnInfo(name = "PhotoPointType")
    private int PhotoPointType;

    public String getPhotoPointID() {
        return PhotoPointID;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public int getPhotoPointType() {
        return PhotoPointType;
    }

    public void setPhotoPointID(String photoPointID) {
        PhotoPointID = photoPointID;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public void setPhotoPointType(int photoPointType) {
        PhotoPointType = photoPointType;
    }
}

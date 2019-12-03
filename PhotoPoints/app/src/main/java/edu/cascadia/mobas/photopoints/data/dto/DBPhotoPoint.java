package edu.cascadia.mobas.photopoints.data.dto;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "PhotoPoints")
public class DBPhotoPoint {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name ="PhotoPointID")
    @NonNull
    private int PhotoPointID;

    @ColumnInfo(name = "QRCode")
    private String QRCode;

    @ColumnInfo(name ="Latitude")
    private Double Latitude;

    @ColumnInfo(name ="Longitude")
    private Double Longitude;

    @ColumnInfo(name = "PhotoPointType")
    private int PhotoPointType;

    public int getPhotoPointID() {
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

    public String getQRCode() { return QRCode; }

    public void setQRCode(String QRCode) { this.QRCode = QRCode; }

    public void setPhotoPointID(int photoPointID) {
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

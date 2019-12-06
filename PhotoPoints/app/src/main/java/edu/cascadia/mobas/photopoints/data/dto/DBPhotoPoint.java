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

    @ColumnInfo(name = "ItemID")
    private int ItemID;

    //Empty constructor for Room
    public DBPhotoPoint(){

    }

    public DBPhotoPoint(String QRCode, Double latitude, Double longitude, int photoPointType) {
        this.QRCode = QRCode;
        Latitude = latitude;
        Longitude = longitude;
        PhotoPointType = photoPointType;
    }

    public int getItemID() { return ItemID; }

    public void setItemID(int itemID) { ItemID = itemID; }

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

    //Used the very first time to populate the database.
    public static DBPhotoPoint[] populateData(){
        return new DBPhotoPoint[]{
            new DBPhotoPoint("point001",47.776013, -122.192043, 1),
            new DBPhotoPoint("point002",47.775886, -122.192635, 1),
            new DBPhotoPoint("point003",47.776013, -122.193909, 1),
            new DBPhotoPoint("point004",47.775241, -122.195866, 1),
            new DBPhotoPoint("point005",47.774999, -122.195243, 1),
            new DBPhotoPoint("point006",47.774484, -122.195694, 1),
            new DBPhotoPoint("point007",47.773701, -122.194359, 1),
            new DBPhotoPoint("point008",47.774150, -122.192456, 1),
            new DBPhotoPoint("point009",47.775886, -122.192635, 1)
        };
    }
}

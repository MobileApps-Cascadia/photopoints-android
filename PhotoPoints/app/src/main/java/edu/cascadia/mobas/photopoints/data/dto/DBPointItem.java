package edu.cascadia.mobas.photopoints.data.dto;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import edu.cascadia.mobas.photopoints.data.converters.ItemTypeConverter;
import edu.cascadia.mobas.photopoints.model.ItemType;

@Entity(tableName = "point_item")
public class DBPointItem {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name ="id")
    private int id;

    @ColumnInfo(name = "qr_Code")
    private String qrCode;

    @ColumnInfo(name ="latitude")
    private Double latitude;

    @ColumnInfo(name ="longitude")
    private Double longitude;

    @TypeConverters(ItemTypeConverter.class)
    @ColumnInfo(name = "type")
    private ItemType type;

    @ColumnInfo(name = "inactive")
    private boolean inactive;


    //Empty constructor for Room
    @Ignore
    public DBPointItem(){ }

    // Standard constructor
    public DBPointItem(
            int id,
            ItemType type,
            Double latitude,
            Double longitude,
            String qrCode,
            boolean inactive
    ) {
        this.id = id;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.qrCode = qrCode;
        this.inactive = inactive;
    }

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }


    public ItemType getType() {
        return this.type == null ? ItemType.Unknown : this.type; }

    public void setType(ItemType type) {
        this.type = (type == null ? ItemType.Unknown : type );
    }


    public Double getLatitude() { return this.latitude; }

    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return this.longitude; }

    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public String getQrCode() { return qrCode; }

    public void setQrCode(String qrCode) { this.qrCode = qrCode; }

    public boolean isPhotoPoint() { return !(qrCode == null || qrCode.trim().equals("")); }

    //public boolean getInactive() { return this.inactive; }

    public boolean isActive() { return !this.inactive; }

    public void setInactive() { this.inactive = true; }

    public void setActive() { this.inactive = false; }


    //Used the very first time to populate the database.
    public static DBPointItem[] populateData(){
        return new DBPointItem[]{
        new DBPointItem(1, ItemType.Plant, 47.776013, -122.192043, "https://www.plantsmap.com/organizations/24477/plants/28097", false),
        new DBPointItem(2, ItemType.Plant, 47.775886, -122.192635, "https://www.plantsmap.com/organizations/24477/plants/28069", false),
        new DBPointItem(3, ItemType.Plant, 47.776013, -122.193909, "https://www.plantsmap.com/organizations/24477/plants/28092", false),
        new DBPointItem(4, ItemType.Plant, 47.775241, -122.195866, "https://www.plantsmap.com/organizations/24477/plants/28061", false),
        new DBPointItem(5, ItemType.Plant, 47.774999, -122.195243, "https://www.plantsmap.com/organizations/24477/plants/28074", false),
        new DBPointItem(6, ItemType.Plant, 47.774484, -122.195694, "https://www.plantsmap.com/organizations/24477/plants/28070", false),
        new DBPointItem(7, ItemType.Plant, 47.773701, -122.194359, "https://www.plantsmap.com/plants/28068", false),
        new DBPointItem(8, ItemType.Plant, 47.774150, -122.192456, "https://www.plantsmap.com/organizations/24477/plants/28094", false)
        };
    }




    public boolean isInactive() { return this.inactive; }
}

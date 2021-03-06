package edu.cascadia.mobas.photopoints.data.dto;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import edu.cascadia.mobas.photopoints.data.converters.ItemTypeConverter;
import edu.cascadia.mobas.photopoints.model.Coordinates;
import edu.cascadia.mobas.photopoints.model.ItemTypeEnum;

@Entity(tableName = "point_item")
public class DBPointItem {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name ="id")
    private int id;

    @ColumnInfo(name = "qr_Code")
    private String qrCode;

    @Embedded
    private Coordinates location;

    @TypeConverters(ItemTypeConverter.class)
    @ColumnInfo(name = "type")
    private ItemTypeEnum type;

    @ColumnInfo(name = "inactive")
    private boolean inactive;


    //Empty constructor for Room
    @Ignore
    public DBPointItem(){ }

    // Standard constructor
    public DBPointItem(
            int id,
            ItemTypeEnum type,
            Coordinates location,
            String qrCode,
            boolean inactive
    ) {
        this.id = id;
        this.type = type;
        this.location = location;
        this.qrCode = qrCode;
        this.inactive = inactive;
    }

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }


    public ItemTypeEnum getType() {
        return this.type == null ? ItemTypeEnum.Unknown : this.type; }

    public void setType(ItemTypeEnum type) {
        this.type = (type == null ? ItemTypeEnum.Unknown : type );
    }

    public Coordinates getLocation() { return this.location; }

    public void setLocation(double latitude, double longitude, double altitude) {
        this.location.setLatitude(latitude);
        this.location.setLongitude(longitude);
        this.location.setAltitude(altitude);
    }

    public void setLocation(Coordinates location) { this.location = location; }

    public Double getLatitude() { return this.location.getLatitude(); }

    public void setLatitude(Double latitude) { this.location.setLatitude(latitude); }

    public Double getLongitude() { return this.location.getLongitude(); }

    public void setLongitude(Double longitude) { this.location.setLongitude(longitude); }

    public Double getAltitude() {  return this.location.getAltitude(); }

    public void setAltitude(Double altitude) { this.location.setAltitude(altitude);}

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
        new DBPointItem(1, ItemTypeEnum.Plant, new Coordinates(-122.192043, 47.776013),"https://www.plantsmap.com/organizations/24477/plants/28097", false),
        new DBPointItem(2, ItemTypeEnum.Plant, new Coordinates(-122.192635, 47.775886), "https://www.plantsmap.com/organizations/24477/plants/28069", false),
        new DBPointItem(3, ItemTypeEnum.Plant, new Coordinates(-122.193909, 47.776013), "https://www.plantsmap.com/organizations/24477/plants/28092", false),
        new DBPointItem(4, ItemTypeEnum.Plant, new Coordinates(-122.195866, 47.775241), "https://www.plantsmap.com/organizations/24477/plants/28061", false),
        new DBPointItem(5, ItemTypeEnum.Plant, new Coordinates(-122.195243, 47.774999), "https://www.plantsmap.com/organizations/24477/plants/28074", false),
        new DBPointItem(6, ItemTypeEnum.Plant, new Coordinates(-122.195694, 47.774484), "https://www.plantsmap.com/organizations/24477/plants/28070", false),
        new DBPointItem(7, ItemTypeEnum.Plant, new Coordinates(-122.194359, 47.773701), "https://www.plantsmap.com/plants/28068", false),
        new DBPointItem(8, ItemTypeEnum.Plant, new Coordinates(-122.192456, 47.774150), "https://www.plantsmap.com/organizations/24477/plants/28094", false)
        };
    }




    public boolean isInactive() { return this.inactive; }
}

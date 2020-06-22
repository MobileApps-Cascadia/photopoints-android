package edu.cascadia.mobas.photopoints.data.dto;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import edu.cascadia.mobas.photopoints.model.Coordinates;
import androidx.room.TypeConverters;

@Entity(tableName = "items")
public class ItemEntity {
    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    private String id = "ItemEntity: Missing Id";

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "label")
    private String label;

    @Embedded
    private Coordinates location;

    @ColumnInfo(name = "inactive")
    private boolean inactive;

    @ColumnInfo(name = "qrCode") // TODO:  lookups to get their own table
    private String qrCode;

    @Ignore
    public ItemEntity(){ }

    public ItemEntity(
            @NonNull String id,
            @NonNull String type,
            @NonNull String label,
            @NonNull Coordinates location,
            String qrCode,       // TODO:  lookups to get their own table
            boolean inactive
    ) {
        this.id = id;
        this.type = type;
        this.label = label;
        this.location = location;
        this.qrCode = qrCode;
        this.inactive = inactive;
    }

    @NonNull
    public String getId() { return this.id; }
    public void setId(@NonNull String id) { this.id = id; }

    public void setLabel(@NonNull String label) { this.label = label; }
    public String getLabel() {
        return (this.label == null ? "" : this.label);
    };


    public String getType() {
        return this.type == null ? "" : this.type; }

    public void setType(String type) {
        this.type = (type == null ? "" : type );
    }

    public boolean isInactive() { return this.inactive; }
    public void setInactive(boolean inactive) { this.inactive = inactive; }

    public Coordinates getLocation() { return this.location; }
    public void setLocation(Coordinates location) { this.location = location; }
    public void setLocation(double latitude, double longitude, double altitude) {
        this.location.setLatitude(latitude);
        this.location.setLongitude(longitude);
        this.location.setAltitude(altitude);
    }

    // TODO:  Lookups will be moved to own table
    public String getQrCode() { return qrCode; }
    public void setQrCode(String qrCode) { this.qrCode = qrCode; }



    //Used the very first time to populate the database.
    public static edu.cascadia.mobas.photopoints.data.dto.ItemEntity[] populateData(){
        return new edu.cascadia.mobas.photopoints.data.dto.ItemEntity[]{
                new edu.cascadia.mobas.photopoints.data.dto.ItemEntity("1", "plant", "Item 1", new Coordinates(-122.192043, 47.776013),"https://www.plantsmap.com/organizations/24477/plants/28097", false),
                new edu.cascadia.mobas.photopoints.data.dto.ItemEntity("2", "plant", "Item 2", new Coordinates(-122.192635, 47.775886),"https://www.plantsmap.com/organizations/24477/plants/28069", false),
                new edu.cascadia.mobas.photopoints.data.dto.ItemEntity("3", "plant", "Item 3", new Coordinates(-122.193909, 47.776013),"https://www.plantsmap.com/organizations/24477/plants/28092", false),
                new edu.cascadia.mobas.photopoints.data.dto.ItemEntity("4", "plant", "Item 4", new Coordinates(-122.195866, 47.775241),"https://www.plantsmap.com/organizations/24477/plants/28061", false)
        };
    }

}


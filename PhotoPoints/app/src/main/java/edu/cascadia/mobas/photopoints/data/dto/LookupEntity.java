package edu.cascadia.mobas.photopoints.data.dto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import edu.cascadia.mobas.photopoints.model.Coordinates;

import static androidx.room.ForeignKey.CASCADE;


@Entity(tableName = "lookups")

public class LookupEntity {

    @PrimaryKey
    @ColumnInfo(name = "lookup")
    @NonNull
    private String lookup = "LookupEntity: lookup not set";

    @ColumnInfo(name = "id")
    @NonNull
    private String id = "LookupEntity: id Not Set";

    @Ignore
    public LookupEntity() {}

    public LookupEntity(@NonNull String lookup, @NonNull String id) {
        this.lookup = lookup;
        this.id = id;
    }

    public String getLookup() {
        return lookup;
    }

    public void setLookup(@NonNull String lookup) {
        this.lookup = lookup;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }


    //Used the very first time to populate the database.
    public static edu.cascadia.mobas.photopoints.data.dto.LookupEntity[] populateData() {
        return new edu.cascadia.mobas.photopoints.data.dto.LookupEntity[]{
                new edu.cascadia.mobas.photopoints.data.dto.LookupEntity("https://www.plantsmap.com/organizations/24477/plants/28097", "28097"),
                new edu.cascadia.mobas.photopoints.data.dto.LookupEntity("https://www.plantsmap.com/organizations/24477/plants/28069", "28097"),
                new edu.cascadia.mobas.photopoints.data.dto.LookupEntity("https://www.plantsmap.com/organizations/24477/plants/28092", "28092"),
                new edu.cascadia.mobas.photopoints.data.dto.LookupEntity("https://www.plantsmap.com/organizations/24477/plants/28061", "28061")
        };
    }
}


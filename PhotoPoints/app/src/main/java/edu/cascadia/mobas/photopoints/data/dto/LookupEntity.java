package edu.cascadia.mobas.photopoints.data.dto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

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


}



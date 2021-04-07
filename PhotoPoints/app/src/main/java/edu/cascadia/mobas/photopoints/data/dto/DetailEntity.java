package edu.cascadia.mobas.photopoints.data.dto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

import static androidx.room.ForeignKey.CASCADE;


@Entity(tableName = "details",
        primaryKeys = { "id", "property" },
        foreignKeys = @ForeignKey(entity = ItemEntity.class,
        parentColumns = "id",
        childColumns = "id",
        onDelete = CASCADE))

public class DetailEntity {
    @ColumnInfo(name = "id")
    @NonNull
    private String id;

    @ColumnInfo(name = "property")
    @NonNull
    private String property;

    @ColumnInfo(name = "value")
    private String value;

    @Ignore
    public DetailEntity() {}

    public DetailEntity(@NonNull String id, @NonNull String property, @Nullable String value) {
        this.id = id;
        this.property = property;
        this.value = value;
    }

    public String getId() { return this.id; }
    public void setId(String id) { this.id = id; }

    public String getProperty() { return this.property; }
    public void setProperty(String property) { this.property = property; }

    public String getValue() { return this.value; }
    public void setValue(String value) { this.value = value; }



}



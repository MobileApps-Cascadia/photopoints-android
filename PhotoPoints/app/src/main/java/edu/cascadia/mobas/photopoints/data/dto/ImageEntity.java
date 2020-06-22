package edu.cascadia.mobas.photopoints.data.dto;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "images",
        primaryKeys = {"id","filename"},
        foreignKeys = @ForeignKey(entity = ItemEntity.class, parentColumns = "id", childColumns = "id"))

public class ImageEntity {
    @ColumnInfo(name = "id")
    @NonNull
    private String id;

    @ColumnInfo(name = "filename")
    @NonNull
    private String filename;

    @ColumnInfo(name = "basefile")
    private String url;

    @ColumnInfo(name = "image_type")
    private String imageType;

    @ColumnInfo(name = "priority")
    private int priority;

    @ColumnInfo(name = "width")
    private int width;

    @ColumnInfo(name = "height")
    private int height;

    @ColumnInfo(name = "generated")
    private boolean generated;

    @ColumnInfo(name = "inactive")
    private boolean inactive;

    public ImageEntity(@NonNull String id, @NonNull String filename) {
        this(id, filename, filename);
    }
    public ImageEntity(@NonNull String id, @NonNull String filename, @NonNull String baseFile) {
        this.id = id;
        this.filename = filename;
        this.url = filename;
        this.imageType = "full";
        this.priority = 0;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isGenerated() {
        return generated;
    }

    public void setGenerated(boolean generated) {
        this.generated = generated;
    }

    public boolean isInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }

}




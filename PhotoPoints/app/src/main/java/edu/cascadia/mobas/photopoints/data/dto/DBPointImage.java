package edu.cascadia.mobas.photopoints.data.dto;

import android.media.Image;
import android.provider.ContactsContract;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import edu.cascadia.mobas.photopoints.data.converters.ImageOrientationConverter;
import edu.cascadia.mobas.photopoints.model.PointImage;
import edu.cascadia.mobas.photopoints.model.PointImage.ImageOrientation;

@Entity(tableName = "point_image")
public class DBPointImage {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "image_id")
    private int imageID;

    @ColumnInfo(name = "uri")
    private String uri;

    @TypeConverters(ImageOrientationConverter.class)
    @ColumnInfo(name = "orientation")
    private ImageOrientation imageOrientation;

    @ColumnInfo(name = "height")
    private Double height;

    @ColumnInfo(name = "width")
    private Double width;

    @ColumnInfo(name = "source")
    private String source;

    // is the entity for the foreign class?
    @ColumnInfo(name = "id")
    private int id;

    //Empty constructor for Room
    @Ignore
    public DBPointImage(){}

    // Standard constructor
    public DBPointImage(
            int imageID,
            String uri,
            ImageOrientation imageOrientation,
            Double height,
            Double width,
            String source,
            int id
    ) {
        this.imageID = imageID;
        this.uri = uri;
        this.imageOrientation = imageOrientation;
        this.height = height;
        this.width = width;
        this.source = source;
        this.id = id;
    }

    public int getImageID() {
        return this.imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public ImageOrientation getImageOrientation() {
        return this.imageOrientation;
    }

    public void setImageOrientation(ImageOrientation imageOrientation) {
        this.imageOrientation = imageOrientation;
    }

    public Double getHeight() {
        return this.height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidth() {
        return this.width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

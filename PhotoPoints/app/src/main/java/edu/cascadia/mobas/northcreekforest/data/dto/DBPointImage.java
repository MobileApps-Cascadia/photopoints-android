package edu.cascadia.mobas.northcreekforest.data.dto;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import edu.cascadia.mobas.northcreekforest.data.converters.ImageOrientationConverter;
import edu.cascadia.mobas.northcreekforest.model.PointImage.ImageOrientation;

@Entity(tableName = "point_image", indices = @Index(value = {"id"}, unique = true),  foreignKeys = @ForeignKey(entity = DBPointItem.class, parentColumns = "id", childColumns = "id"))
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

    // is the entity for the foreign class?
    // yes
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
            int id
    ) {
        this.imageID = imageID;
        this.uri = uri;
        this.imageOrientation = imageOrientation;
        this.height = height;
        this.width = width;
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

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static DBPointImage[] populateData() {
        return new DBPointImage[] {
                new DBPointImage(1, "plant_card_1.png", ImageOrientation.Landscape, 130.0, 250.0, 1),
                new DBPointImage(2, "plant_card_1.png", ImageOrientation.Landscape, 130.0, 250.0, 2),
                new DBPointImage(3, "plant_card_1.png", ImageOrientation.Landscape, 130.0, 250.0, 3),
                new DBPointImage(4, "plant_card_1.png", ImageOrientation.Landscape, 130.0, 250.0, 4),
                new DBPointImage(5, "plant_card_1.png", ImageOrientation.Landscape, 130.0, 250.0, 5),
                new DBPointImage(6, "plant_card_1.png", ImageOrientation.Landscape, 130.0, 250.0, 6),
                new DBPointImage(7, "plant_card_1.png", ImageOrientation.Landscape, 130.0, 250.0, 7),
                new DBPointImage(8, "plant_card_1.png", ImageOrientation.Landscape, 130.0, 250.0, 8)
        };
    }

}

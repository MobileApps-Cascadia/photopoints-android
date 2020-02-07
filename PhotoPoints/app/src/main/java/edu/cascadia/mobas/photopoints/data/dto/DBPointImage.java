package edu.cascadia.mobas.photopoints.data.dto;

import android.media.Image;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import edu.cascadia.mobas.photopoints.data.converters.ImageOrientationConverter;
import edu.cascadia.mobas.photopoints.model.PointImage;
import edu.cascadia.mobas.photopoints.model.PointImage.ImageOrientation;
import edu.cascadia.mobas.photopoints.model.PointItem;

@Entity(tableName = "point_image")
public class DBPointImage {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "image_id")
    private int imageID;

    @ColumnInfo(name = "uri")
    private String URI;

    @TypeConverters(ImageOrientationConverter.class)
    @ColumnInfo(name = "orientation")
    private ImageOrientation orientation;

    @ColumnInfo(name = "height")
    private Double height;

    @ColumnInfo(name = "width")
    private Double width;

    @ColumnInfo(name = "source")
    private String source;

    // is the entity for the foreign class?
    @ColumnInfo(name = "id")
    private int id;
}

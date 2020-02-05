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
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "URI")
    private String URI;

    @TypeConverters(ImageOrientationConverter.class)
    @ColumnInfo(name = "Orientation")
    private ImageOrientation orientation;

    @ColumnInfo(name = "Height")
    private Double height;

    @ColumnInfo(name = "Width")
    private Double width;

    @ColumnInfo(name = "Source")
    private String source;
}

package edu.cascadia.mobas.photopoints.data.converters;

import android.media.Image;
import android.provider.ContactsContract;

import androidx.room.TypeConverter;

import edu.cascadia.mobas.photopoints.model.ItemType;
import edu.cascadia.mobas.photopoints.model.PointImage.ImageOrientation;


// This type converter allows the database to store the ItemType enum as string
// and the entitity to store it as a proper ItemType enum


public class ImageOrientationConverter {

    @TypeConverter
    public static String imageOrientationToString(ImageOrientation orientation) {
        return orientation.name();
    }

    @TypeConverter
    public static ImageOrientation stringToImageOrientation(String orientationString) {
        for (ImageOrientation orientation : ImageOrientation.values()) {
            if (orientation.name().equals(orientationString)) return orientation;
        }
        return ImageOrientation.Unknown;
    }
}

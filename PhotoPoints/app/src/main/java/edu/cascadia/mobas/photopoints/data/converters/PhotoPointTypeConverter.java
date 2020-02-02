package edu.cascadia.mobas.photopoints.data.converters;

import androidx.room.TypeConverter;
import edu.cascadia.mobas.photopoints.model.PhotoPoint.PhotoPointType;



// This type converter allows the database to store the PhotoPointType enum as string
// and the entitity to store it as a proper PhotoPointType enum



public class PhotoPointTypeConverter {

    @TypeConverter
    public static String photoPointTypeToString(PhotoPointType type) {
        return type.name();
    }

    @TypeConverter
    public static PhotoPointType stringToPhotoPointType(String typeString) {
        for (PhotoPointType type : PhotoPointType.values()) {
            if (type.name().equals(typeString)) return type;
        }
        return PhotoPointType.Unknown;
    }
}

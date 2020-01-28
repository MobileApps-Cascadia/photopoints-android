package edu.cascadia.mobas.photopoints.data.converters;

import androidx.room.TypeConverter;

public class BooleanTypeConverter {
    @TypeConverter
    public static Boolean fromLong(Long value) {
        return value != 0;
    }
    @TypeConverter
    public static int fromBoolean(Boolean value) {
        return value == false ? 0 : 1;
    }
}
package edu.cascadia.mobas.northcreekforest.data.converters;

import androidx.room.TypeConverter;
import java.util.Date;

//SQL does not have data types of Date or Date Time. Therefore we need to convert the dates from and to the database.
//This Converter is implemented on the database and will be used for all Date types.
public class TimeStampConverter {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}

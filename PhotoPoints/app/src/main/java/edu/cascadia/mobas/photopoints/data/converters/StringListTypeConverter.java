package edu.cascadia.mobas.photopoints.data.converters;




import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;





// This type converter allows the database to store a list as a semicolon-separated string


public class StringListTypeConverter {

    @TypeConverter
    public static String listToString(List<String> list) {
        StringBuilder result = new StringBuilder();
        for (String item : list) {
            result.append(item).append("; ");
        }
        // remove trailing characters
        result.setLength(result.length() - 2);
        return result.toString();
    }

    @TypeConverter
    public static List<String> stringToList(String str) {
        List<String> list = new ArrayList<>();
        String[] items = str.split(";");

        for ( String item : items ) {
            list.add(item.trim());
        }
        return list;
    }
}
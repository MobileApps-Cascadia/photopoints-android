package edu.cascadia.mobas.photopoints.data.converters;

import androidx.room.TypeConverter;
import edu.cascadia.mobas.photopoints.model.ItemTypeEnum;



// This type converter allows the database to store the ItemType enum as string
// and the entitity to store it as a proper ItemType enum


public class ItemTypeConverter {

    @TypeConverter
    public static String itemTypeToString(ItemTypeEnum type) {
        return type.name();
    }

    @TypeConverter
    public static ItemTypeEnum stringToItemType(String typeString) {
        for (ItemTypeEnum type : ItemTypeEnum.values()) {
            if (type.name().equals(typeString)) return type;
        }
        return ItemTypeEnum.Unknown;
    }
}

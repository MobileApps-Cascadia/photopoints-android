package edu.cascadia.mobas.northcreekforest.data.converters;

import androidx.room.TypeConverter;
import edu.cascadia.mobas.northcreekforest.model.ItemType;



// This type converter allows the database to store the ItemType enum as string
// and the entitity to store it as a proper ItemType enum


public class ItemTypeConverter {

    @TypeConverter
    public static String itemTypeToString(ItemType type) {
        return type.name();
    }

    @TypeConverter
    public static ItemType stringToItemType(String typeString) {
        for (ItemType type : ItemType.values()) {
            if (type.name().equals(typeString)) return type;
        }
        return ItemType.Unknown;
    }
}

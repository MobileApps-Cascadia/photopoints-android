package edu.cascadia.mobas.photopoints.data.dto;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;
import java.util.Objects;

import edu.cascadia.mobas.photopoints.data.dto.DetailEntity;
import edu.cascadia.mobas.photopoints.data.dto.ImageEntity;
import edu.cascadia.mobas.photopoints.data.dto.ItemEntity;

public class ItemRecord {
    @Embedded
    public ItemEntity itemEntity;
    @Relation(parentColumn = "id", entityColumn = "id", entity = DetailEntity.class)
    List<DetailEntity> detailEntityList;
    @Relation(parentColumn = "id", entityColumn = "id", entity = ImageEntity.class)
    List<ImageEntity> imageEntityList;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ItemRecord)) return false;
        ItemRecord that = (ItemRecord) obj;
        return itemEntity.equals(that.itemEntity) &&
                detailEntityList.equals(that.detailEntityList) &&
                imageEntityList.equals(that.imageEntityList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemEntity, detailEntityList, imageEntityList);
    }
}

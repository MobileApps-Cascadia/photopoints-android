package edu.cascadia.mobas.photopoints.data.dto;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import edu.cascadia.mobas.photopoints.data.dto.DetailEntity;
import edu.cascadia.mobas.photopoints.data.dto.ImageEntity;
import edu.cascadia.mobas.photopoints.data.dto.ItemEntity;

public class ItemRecord {
    @Embedded
    public ItemEntity imageEntity;
    @Relation(parentColumn = "id", entityColumn = "id", entity = DetailEntity.class)
    List<DetailEntity> detailEntityList;
    @Relation(parentColumn = "id", entityColumn = "id", entity = ImageEntity.class)
    List<ImageEntity> imageEntityList;
}

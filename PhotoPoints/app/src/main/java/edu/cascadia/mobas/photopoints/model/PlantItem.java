package edu.cascadia.mobas.photopoints.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import edu.cascadia.mobas.photopoints.data.dto.DBPlant;
import edu.cascadia.mobas.photopoints.data.dto.DBPointItem;

public class PlantItem {
    @Embedded
    public DBPointItem point;
    @Relation(parentColumn = "id", entityColumn = "plant_id", entity = DBPlant.class)
    public DBPlant plant;
}


package edu.cascadia.mobas.photopoints.data.dto;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Plants")
public class DBPlant {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="PlantID")
    @NonNull
    private int PlantId;

    @ColumnInfo(name="Species")
    private String Species;

    @ColumnInfo(name="Description")
    private String Description;

    public int getPlantId() {
        return PlantId;
    }

    public void setPlantId(int plantId) {
        PlantId = plantId;
    }

    public String getSpecies() {
        return Species;
    }

    public void setSpecies(String species) {
        Species = species;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }
}

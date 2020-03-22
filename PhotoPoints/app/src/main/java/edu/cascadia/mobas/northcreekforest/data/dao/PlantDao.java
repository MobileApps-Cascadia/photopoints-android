package edu.cascadia.mobas.northcreekforest.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import edu.cascadia.mobas.northcreekforest.data.dto.DBPlant;

@Dao
public interface PlantDao {
    @Query("SELECT plant_id, description, species, common_names FROM Plants")
    List<DBPlant> getPlants();

    @Insert
    void insert(DBPlant plant);

    @Insert
    void insertAll(DBPlant... plants);

    @Delete
    void delete(DBPlant plant);

    @Update
    void update(DBPlant plant);
}

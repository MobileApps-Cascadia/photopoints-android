package edu.cascadia.mobas.photopoints.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import edu.cascadia.mobas.photopoints.data.dto.DBPlant;

@Dao
public interface PlantDao {
    @Query("SELECT PlantID, Description, Species FROM Plants")
    List<DBPlant> getPlants();

    @Insert
    void insert(DBPlant plant);

    @Delete
    void delete(DBPlant plant);

    @Update
    void update(DBPlant plant);
}

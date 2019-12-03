package edu.cascadia.mobas.photopoints.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import edu.cascadia.mobas.photopoints.data.dto.DBPhotoPoint;

@Dao
public interface PhotoPointDao {

    @Query("Select PhotoPointID, PhotoPointType, Latitude, Longitude FROM PhotoPoints")
    List<DBPhotoPoint> getPhotoPoints();

    @Insert
    void insert(DBPhotoPoint photoPoint);

    @Delete
    void delete(DBPhotoPoint photoPoint);

    @Update
    void update(DBPhotoPoint photoPoint);
}

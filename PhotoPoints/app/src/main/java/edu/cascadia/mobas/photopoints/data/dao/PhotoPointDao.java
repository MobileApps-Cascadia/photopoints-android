package edu.cascadia.mobas.photopoints.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import edu.cascadia.mobas.photopoints.data.dto.DBPhotoPoint;
import edu.cascadia.mobas.photopoints.model.PhotoPoint;

@Dao
public interface PhotoPointDao {

    @Query("Select PhotoPointID, PhotoPointType, Latitude, Longitude, QRCode FROM PhotoPoints")
    List<DBPhotoPoint> getAll();

    @Insert
    void insert(DBPhotoPoint photoPoint);

    @Delete
    void delete(DBPhotoPoint photoPoint);

    @Update
    void update(DBPhotoPoint photoPoint);

    @Insert
    void insertAll(DBPhotoPoint... photoPoints);

    @Query("Select COUNT(*) FROM PhotoPoints")
    Integer getCount();

    @Query("Select PhotoPointID FROM PhotoPoints WHERE QRCode = :qrCode")
    Integer getIDByQRCode(String qrCode);

    @Query("Select PhotoPointID, PhotoPointType, Latitude, Longitude, QRCode FROM PhotoPoints WHERE PhotoPointID = :id")
    DBPhotoPoint getById(Integer id);
}

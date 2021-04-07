package edu.cascadia.mobas.photopoints.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import edu.cascadia.mobas.photopoints.data.dto.ImageEntity;

@Dao
public interface ImageDao {
    @Query("SELECT * FROM images")
    List<ImageEntity> getAllImages();

    @Query("SELECT * FROM images WHERE id=:id")
    List<ImageEntity> getImages(String id);

    @Insert
    void insert(ImageEntity imageEntity);

    @Insert
    void insertAll(ImageEntity... imageEntities);

    @Delete
    void delete(ImageEntity imageEntity);

    @Update
    void update(ImageEntity imageEntity);
}
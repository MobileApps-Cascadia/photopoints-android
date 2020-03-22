package edu.cascadia.mobas.northcreekforest.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.cascadia.mobas.northcreekforest.data.dto.DBPointImage;

@Dao
public interface PointImageDao {

    @Query("Select * FROM point_image")
    List<DBPointImage> getAll();

    @Insert
    void insert(DBPointImage dbPointImage);

    @Delete
    void delete(DBPointImage dbPointImage);

    @Update
    void update(DBPointImage dbPointImage);

    @Insert
    void insertAll(DBPointImage... dbPointImages);

    @Query("Select COUNT(*) FROM point_image")
    Integer getCount();

    // TODO:  Handle empty result
    @Query("Select image_id FROM point_image WHERE URI = :uri")
    Integer getIDByURI(String uri);

    // TODO:  Handle empty result
    @Query("Select * FROM point_image WHERE image_id = :imageID")
    DBPointImage getById(Integer imageID);
}

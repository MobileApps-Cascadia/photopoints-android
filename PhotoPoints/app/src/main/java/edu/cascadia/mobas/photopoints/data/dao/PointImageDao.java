package edu.cascadia.mobas.photopoints.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cascadia.mobas.photopoints.data.converters.ImageOrientationConverter;

import java.util.List;
import edu.cascadia.mobas.photopoints.data.dto.DBPointImage;

@Dao
public interface PointImageDao {

    @Query("Select * FROM point_item")
    List<DBPointImage> getAll();

    @Insert
    void insert(DBPointImage dbPointItem);

    @Delete
    void delete(DBPointImage dbPointItem);

    @Update
    void update(DBPointImage dbPointItem);

    @Insert
    void insertAll(DBPointImage... dbPointItems);

    @Query("Select COUNT(*) FROM point_image")
    Integer getCount();

    // TODO:  Handle empty result
    @Query("Select id FROM point_image WHERE URI = :URI")
    Integer getIDByURI(String URI);

    // TODO:  Handle empty result
    @Query("Select * FROM point_image WHERE id = :id")
    DBPointImage getById(Integer id);
}

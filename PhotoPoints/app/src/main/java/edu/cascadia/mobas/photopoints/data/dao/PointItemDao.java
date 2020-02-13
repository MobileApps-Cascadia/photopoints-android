package edu.cascadia.mobas.photopoints.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cascadia.mobas.photopoints.data.converters.ItemTypeConverter;

import java.util.List;
import edu.cascadia.mobas.photopoints.data.dto.DBPointItem;
import edu.cascadia.mobas.photopoints.model.ItemType;
import edu.cascadia.mobas.photopoints.model.PlantItem;

@Dao
public interface PointItemDao {

    @Query("Select * FROM point_item")
    List<DBPointItem> getAll();

    @Transaction
    @Query(value = "SELECT * FROM point_item"
            + " WHERE type = 'PLANT'"
            + " AND inactive = 0")
    List<PlantItem> getAllPlants();

    @Insert
    void insert(DBPointItem dbPointItem);

    @Delete
    void delete(DBPointItem dbPointItem);

    @Update
    void update(DBPointItem dbPointItem);

    @Insert
    void insertAll(DBPointItem... dbPointItems);

    @Query("Select COUNT(*) FROM point_item")
    Integer getCount();

    // TODO:  Handle empty result
    @Query("Select id FROM point_item WHERE qr_code = :qrCode")
    Integer getIDByQRCode(String qrCode);

    // TODO:  Handle empty result
    @Query("Select * FROM point_item WHERE id = :id")
    DBPointItem getById(Integer id);
}

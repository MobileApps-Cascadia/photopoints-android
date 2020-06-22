package edu.cascadia.mobas.photopoints.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import edu.cascadia.mobas.photopoints.data.dto.ItemEntity;
import edu.cascadia.mobas.photopoints.data.dto.ItemRecord;

@Dao
public interface ItemDao {
    @Query("SELECT id, type, label, location FROM items where inactive = 0 ")
    List<ItemRecord> getAllItemRecords();

    @Insert
    void insert(ItemEntity itemEntity);

    @Insert
    void insertAll(ItemEntity... itemEntities);

    @Delete
    void delete(ItemEntity itemEntity);

    @Update
    void update(ItemEntity itemEntity);
}
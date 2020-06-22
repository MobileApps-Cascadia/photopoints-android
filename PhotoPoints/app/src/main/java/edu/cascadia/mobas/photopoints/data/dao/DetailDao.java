package edu.cascadia.mobas.photopoints.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import edu.cascadia.mobas.photopoints.data.dto.DetailEntity;

@Dao
public interface DetailDao {
    @Query("SELECT * FROM details")
    List<DetailEntity> getAllItemsDetails();

    @Query("SELECT * FROM details WHERE id=:id")
    List<DetailEntity> getDetails(String id);

    @Insert
    void insert(DetailEntity detailEntity);

    @Insert
    void insertAll(DetailEntity... detailEntities);

    @Delete
    void delete(DetailEntity detailEntity);

    @Update
    void update(DetailEntity detailEntity);
}
package edu.cascadia.mobas.photopoints.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.cascadia.mobas.photopoints.data.dto.LookupEntity;

@Dao
public interface LookupDao {
    @Query("SELECT * FROM lookups")
    List<LookupEntity> getAllLookups();

    @Query("SELECT * FROM lookups WHERE lookup=:lookup")
    String getIdFromLookup(String lookup);

    @Insert
    void insert(LookupEntity lookupEntity);

    @Insert
    void insertAll(LookupEntity... lookupEntities);

    @Delete
    void delete(LookupEntity lookupEntity);

    @Update
    void update(LookupEntity lookupEntity);
}
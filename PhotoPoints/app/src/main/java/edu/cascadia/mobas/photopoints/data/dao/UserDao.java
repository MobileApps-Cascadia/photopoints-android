package edu.cascadia.mobas.photopoints.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import edu.cascadia.mobas.photopoints.data.dto.DBUser;

@Dao
public interface UserDao {

    //We should only ever store one user, which is the user of the app.
    @Query("Select UserID, FirstName, LastName, DateOfBirth, EmailAddress From Users LIMIT 1")
    DBUser getCurrentUser();

    @Insert
    void insert(DBUser user);

    @Delete
    void delete(DBUser user);

    @Update
    void update(DBUser user);
}

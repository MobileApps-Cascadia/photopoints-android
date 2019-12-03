package edu.cascadia.mobas.photopoints.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import edu.cascadia.mobas.photopoints.data.converters.TimeStampConverter;
import edu.cascadia.mobas.photopoints.data.dao.PhotoPointDao;
import edu.cascadia.mobas.photopoints.data.dao.UserDao;
import edu.cascadia.mobas.photopoints.data.dto.DBPhotoPoint;
import edu.cascadia.mobas.photopoints.data.dto.DBUser;

//Database for the photopoints.
//For now, we can set the ExportSchema flag to false. We might want to set this to true later if we want to start using migrations.
@Database(entities = {DBUser.class, DBPhotoPoint.class}, version = 1, exportSchema = false)
@TypeConverters({TimeStampConverter.class})
public abstract class PhotoPointsDatabase extends RoomDatabase {

    //Cache the instance.
    private static PhotoPointsDatabase mInstance;

    public abstract UserDao userDao();
    public abstract PhotoPointDao photoPointDao();

    //Gets, or creates when not exists, an instance of the PhotoPoints database
    public static PhotoPointsDatabase getAppDatabase(Context context){
        if(mInstance != null){
            return mInstance;
        }

        mInstance = Room.databaseBuilder(context,
                PhotoPointsDatabase.class,
                "photopoints-database")
                .build();

        return mInstance;
    }

    //Destroys the database instance.
    public static void destroyInstance(){
        mInstance = null;
    }
}

package edu.cascadia.mobas.photopoints.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.concurrent.Executors;
import edu.cascadia.mobas.photopoints.R;
import edu.cascadia.mobas.photopoints.data.converters.TimeStampConverter;
import edu.cascadia.mobas.photopoints.data.dao.PhotoPointDao;
import edu.cascadia.mobas.photopoints.data.dao.PlantDao;
import edu.cascadia.mobas.photopoints.data.dao.UserDao;
import edu.cascadia.mobas.photopoints.data.dto.DBPhotoPoint;
import edu.cascadia.mobas.photopoints.data.dto.DBPlant;
import edu.cascadia.mobas.photopoints.data.dto.DBUser;

//Database for the photopoints.
//For now, we can set the ExportSchema flag to false. We might want to set this to true later if we want to start using migrations.
@Database(entities = {DBUser.class, DBPhotoPoint.class, DBPlant.class}, version = 1, exportSchema = false)
@TypeConverters({TimeStampConverter.class})
public abstract class PhotoPointsDatabase extends RoomDatabase {

    //Cache the instance.
    private static PhotoPointsDatabase mInstance;

    public abstract UserDao userDao();
    public abstract PhotoPointDao photoPointDao();
    public abstract PlantDao plantsDao();

    //Gets, or creates when not exists, an instance of the PhotoPoints database
    public static PhotoPointsDatabase getAppDatabase(Context context){
        if(mInstance != null){
            return mInstance;
        }

        mInstance = buildDatbase(context);
        return mInstance;
    }

    private static PhotoPointsDatabase buildDatbase(final Context context){
        return Room.databaseBuilder(context,
                PhotoPointsDatabase.class,
                context.getString(R.string.app_name))
                .addCallback(new Callback() {

                    //The onCreate method is called the first time Room tries to create the database.
                    //This means we can insert "initial data" into the database at this point.
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            //This will run in a separate thread and add the initial data.
                            public void run() {
                                getAppDatabase(context).photoPointDao().insertAll(DBPhotoPoint.populateData());
                            }
                        });
                    }
                })
                .build();
    }

    //Destroys the database instance.
    public static void destroyInstance(){
        mInstance = null;
    }
}

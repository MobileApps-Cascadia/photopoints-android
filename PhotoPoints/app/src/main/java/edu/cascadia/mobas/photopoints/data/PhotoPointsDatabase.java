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
import edu.cascadia.mobas.photopoints.data.dao.PointImageDao;
import edu.cascadia.mobas.photopoints.data.dao.PointItemDao;
import edu.cascadia.mobas.photopoints.data.dao.PlantDao;
import edu.cascadia.mobas.photopoints.data.dao.UserDao;
import edu.cascadia.mobas.photopoints.data.dto.DBPointImage;
import edu.cascadia.mobas.photopoints.data.dto.DBPointItem;
import edu.cascadia.mobas.photopoints.data.dto.DBPlant;
import edu.cascadia.mobas.photopoints.data.dto.DBUser;
import edu.cascadia.mobas.photopoints.data.dto.DetailEntity;
import edu.cascadia.mobas.photopoints.data.dto.ImageEntity;
import edu.cascadia.mobas.photopoints.data.dto.ItemEntity;
import edu.cascadia.mobas.photopoints.data.dto.LookupEntity;
import edu.cascadia.mobas.photopoints.model.PointImage;


//Database for the photopoints.


// This class creates a creates is a singleton instance for the AppDatabase.
// To obtain a reference to the database, getAppDatabase(context) is called.
// If the database does not exist, locally, it is created by room and populated
// with initial data



//For now, we can set the ExportSchema flag to false. We might want to set this to true later if we want to start using migrations.
@Database(entities = {
        DBUser.class, DBPointItem.class, DBPointImage.class, DBPlant.class,
        ItemEntity.class, DetailEntity.class, ImageEntity.class, LookupEntity.class},
        version = 3, exportSchema = false)

@TypeConverters({TimeStampConverter.class})

public abstract class PhotoPointsDatabase extends RoomDatabase {

    //Cache the instance.
    private static PhotoPointsDatabase mInstance;

    public abstract UserDao userDao();
    public abstract PointItemDao pointItemDao();
    public abstract PointImageDao pointImageDao();
    public abstract PlantDao plantDao();

    //Gets, or creates when not exists, an instance of the PhotoPoints database
    public static PhotoPointsDatabase getAppDatabase(Context context){
        if(mInstance != null){
            return mInstance;
        }

        mInstance = buildDatabase(context);
        return mInstance;
    }

    private static PhotoPointsDatabase buildDatabase(final Context context){
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
                                getAppDatabase(context).pointItemDao().insertAll(DBPointItem.populateData());
//                                getAppDatabase(context).pointImageDao().insertAll(DBPointImage.populateData());
                                getAppDatabase(context).plantDao().insertAll(DBPlant.populateData());
                            }
                        });
                    }
                })
                .allowMainThreadQueries()         // TODO: Use threads for all queries
                .fallbackToDestructiveMigration()
                .build();
    }

    //Destroys the database instance.
    public static void destroyInstance(){
        mInstance = null;
    }
}

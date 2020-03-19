package edu.cascadia.mobas.photopoints.repo;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import edu.cascadia.mobas.photopoints.AppExecutors;
import edu.cascadia.mobas.photopoints.data.PhotoPointsDatabase;
import edu.cascadia.mobas.photopoints.model.PlantItem;

public class Repository {

    // the instance for this singleton
    private static Repository repo = null;


    private AppExecutors exec;
    private PhotoPointsDatabase db;
    private MutableLiveData<List<PlantItem>> mPlantItems;

    private Repository(Context context) {
        if (exec == null) { exec = new AppExecutors(); }
        if (db == null) { db = PhotoPointsDatabase.getAppDatabase(context); }
        repo = this;
    }


    // TODO:  determine if its ever appropriate to return an instance like this
    private static Repository getInstance() {
        return repo;
    }

    private static Repository getInstance(Context context) {
        if (repo == null) {
            return new Repository(context);
        } else {
            return repo;
        }
    }

    private MutableLiveData<List<PlantItem>> getPlantsFromDatabase() {

        if (mPlantItems != null) { return mPlantItems; }
        //get livedata from room
        //store in mPlantItems
        mPlantItems = new MutableLiveData<>(db.pointItemDao().getAllPlants());
        return mPlantItems;
    }
}

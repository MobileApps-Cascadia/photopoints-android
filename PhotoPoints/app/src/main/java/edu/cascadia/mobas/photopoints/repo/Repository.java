package edu.cascadia.mobas.photopoints.repo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import edu.cascadia.mobas.photopoints.R;
import edu.cascadia.mobas.photopoints.data.PhotoPointsDatabase;
import edu.cascadia.mobas.photopoints.model.PlantItem;

public class Repository {

    // the instance for this singleton
    private static Repository repo = null;
    private PhotoPointsDatabase db;
    private MutableLiveData<List<PlantItem>> mPlantItems;
    private Context mContext;

    private Repository(Context context) {
        if (db == null) { db = PhotoPointsDatabase.getAppDatabase(context); }
        mPlantItems = getPlantsFromDatabase();
        mContext = context;
        repo = this;
    }


    public static Repository getInstance(Context context) {
        if (repo == null) {
            return new Repository(context);
        } else {
            return repo;
        }
    }

    public MutableLiveData<List<PlantItem>> getPlantsFromDatabase() {

        if (mPlantItems != null) { return mPlantItems; }
        mPlantItems = new MutableLiveData<>();
        mPlantItems.setValue(db.pointItemDao().getAllPlants());
        return mPlantItems;
    }

    public MutableLiveData<List<PlantItem>> getPlants() {
        return mPlantItems;
    }

    public Bitmap getImage(int id) {
        switch (id) {
            case 1: return BitmapFactory.decodeResource(mContext.getResources(), R.raw.plant_card_1);
            case 2: return BitmapFactory.decodeResource(mContext.getResources(), R.raw.plant_card_2);
            case 3: return BitmapFactory.decodeResource(mContext.getResources(), R.raw.plant_card_3);
            case 4: return BitmapFactory.decodeResource(mContext.getResources(), R.raw.plant_card_4);
            case 5: return BitmapFactory.decodeResource(mContext.getResources(), R.raw.plant_card_5);
            case 6: return BitmapFactory.decodeResource(mContext.getResources(), R.raw.plant_card_6);
            case 7: return BitmapFactory.decodeResource(mContext.getResources(), R.raw.plant_card_7);
            case 8: return BitmapFactory.decodeResource(mContext.getResources(), R.raw.plant_card_8);
        }
        return null;
    }

}

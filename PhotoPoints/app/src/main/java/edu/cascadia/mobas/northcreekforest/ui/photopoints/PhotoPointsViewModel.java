package edu.cascadia.mobas.northcreekforest.ui.photopoints;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import edu.cascadia.mobas.northcreekforest.model.PlantItem;
import edu.cascadia.mobas.northcreekforest.repo.Repository;

public class PhotoPointsViewModel extends ViewModel {

    private MutableLiveData<List<PlantItem>> mPlantItems;
    private Repository repo;
    public PhotoPointsViewModel() {
        mPlantItems = repo.getPlants();
    }

    public LiveData<List<PlantItem>> getPlants() {
        return mPlantItems;
    }
}

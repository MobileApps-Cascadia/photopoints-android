package edu.cascadia.mobas.northcreekforest.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.cascadia.mobas.northcreekforest.model.Plant;

public class PlantRepository implements IRepository<Plant> {

    private static Map<Integer,Plant> mPlants = new HashMap<>();


    // Add static data upon first instantiation
    public PlantRepository() {

        // initialize using sample data
        if (mPlants.size() == 0) {
            SampleData.addSamplePlants(mPlants);

        }
    }

    @Override
    public List<Plant> getAll() {
        return new ArrayList<>(mPlants.values());
    }

    // @Override after adding method to Interface
    public Plant getById(int id) {
        return mPlants.get(id);
    }

    @Override
    public Integer count() {
        return mPlants.size();
    }

}

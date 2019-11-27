package edu.cascadia.mobas.photopoints.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.cascadia.mobas.photopoints.model.GeoCoordinate;
import edu.cascadia.mobas.photopoints.model.Path;

public class PathsRepository implements Repository<Path> {

    static String [] samplePathIDs = {};
    private static Map<String,Path> mPaths = new HashMap<>();

    // Add static data upon instantiation
    public PathsRepository() {

        // initialize using sample data
        if (mPaths.size() == 0) {
            SampleData.addSamplePaths(mPaths, "path001,path002,path003,path004,path005,path006");
        }
    }

    @Override
    public List<Path> getAll() {
        return new ArrayList<>(mPaths.values());
    }

    // @Override after adding method to Interface
    public Path getById(String id) {
        return mPaths.get(id);
    }

    @Override
    public int count() {
        return mPaths.size();
    }

}

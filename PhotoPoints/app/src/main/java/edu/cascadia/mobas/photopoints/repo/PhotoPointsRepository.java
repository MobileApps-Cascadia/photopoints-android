package edu.cascadia.mobas.photopoints.repo;

import java.util.ArrayList;
import java.util.List;

import edu.cascadia.mobas.photopoints.model.PhotoPoint;

public class PhotoPointsRepository implements Repository<PhotoPoint> {

    // repository data store
    private static List<PhotoPoint> mPhotoPoints = new ArrayList<>();


    // constructors
    public PhotoPointsRepository() {
        // Add a selection of sample data to the repo
        SampleData.addSamplePhotoPoints(
                mPhotoPoints,
                "point001,point002,point003,point004,point005,point006,"
                + "point007,point008,point009"
        );
    }

    @Override
    public List<PhotoPoint> getAll() {
        return mPhotoPoints;
    }

    public int count(){
        return mPhotoPoints.size();
    }
}
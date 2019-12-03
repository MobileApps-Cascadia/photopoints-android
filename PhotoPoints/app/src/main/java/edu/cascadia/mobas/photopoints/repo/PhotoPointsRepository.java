package edu.cascadia.mobas.photopoints.repo;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import edu.cascadia.mobas.photopoints.data.PhotoPointsDatabase;
import edu.cascadia.mobas.photopoints.data.dto.DBPhotoPoint;
import edu.cascadia.mobas.photopoints.model.PhotoPoint;

public class PhotoPointsRepository implements Repository<PhotoPoint> {

    // repository data store
    private static List<PhotoPoint> mPhotoPoints = new ArrayList<>();


    // constructors
    public PhotoPointsRepository() {
        //Check if the list is empty. If so, add dummy data.
        if(mPhotoPoints.size() == 0){
            SampleData.addSamplePhotoPoints(mPhotoPoints);
        }
    }

    private Context mContext;

    public PhotoPointsRepository(Context context){
        mContext = context;
    }

    @Override
    public List<PhotoPoint> getAll() {
        return mPhotoPoints;
    }

    public int count(){
        return mPhotoPoints.size();
    }

    //TODO: Add data to database and replace getAll() by this method.
    public List<PhotoPoint> getAllFromDB() {
        return map(PhotoPointsDatabase.getAppDatabase(mContext).photoPointDao().getPhotoPoints());
    }

    //TODO: Investigate mapper libraries that can help us remove this boilerplate code.
    //Mapper to return a list of PhotoPoints mapped from the DBModel.
    private List<PhotoPoint> map(List<DBPhotoPoint> dbPoints) {

        List<PhotoPoint> points = new ArrayList<>();

        for(DBPhotoPoint point : dbPoints){
            points.add(new PhotoPoint(point.getPhotoPointID(), point.getLatitude(), point.getLongitude(), point.getQRCode(), point.getPhotoPointType()));
        }

        return points;
    }
}
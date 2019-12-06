package edu.cascadia.mobas.photopoints.repo;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import edu.cascadia.mobas.photopoints.data.PhotoPointsDatabase;
import edu.cascadia.mobas.photopoints.data.dto.DBPhotoPoint;
import edu.cascadia.mobas.photopoints.model.PhotoPoint;

public class PhotoPointsRepository implements Repository<PhotoPoint> {

    private final String TAG = "PhotoPointsRepo";

    // repository data store
    private Context mContext;

    public PhotoPointsRepository(Context context){
        mContext = context;
    }

    @Override
    public List<PhotoPoint> getAll() {
        return map(PhotoPointsDatabase.getAppDatabase(mContext).photoPointDao().getAll());
    }

    @Override
    public Integer count(){
        return PhotoPointsDatabase.getAppDatabase(mContext).photoPointDao().getCount();
    }

    public PhotoPoint getById(Integer id){
        try{
            return mapSingle(PhotoPointsDatabase.getAppDatabase(mContext).photoPointDao().getById(id));
        }
        catch(Exception ex){
            Log.d(TAG, ex.getMessage());
            return null;
        }
    }

    public Integer getIDByQRCode(String qrCode){
        try{
            Integer id = PhotoPointsDatabase.getAppDatabase(mContext).photoPointDao().getIDByQRCode(qrCode);
            return id == null ? 0 : id;
        }
        catch(Exception ex){
            Log.d(TAG, ex.getMessage());
            return 0;
        }
    }

    private PhotoPoint mapSingle(DBPhotoPoint point){
        if(point == null){
            return null;
        }

        return new PhotoPoint(point.getPhotoPointID(), point.getLatitude(), point.getLongitude(), point.getQRCode(), point.getPhotoPointType(), point.getItemID());
    }

    //TODO: Investigate mapper libraries that can help us remove this boilerplate code.
    //Mapper to return a list of PhotoPoints mapped from the DBModel.
    private List<PhotoPoint> map(List<DBPhotoPoint> dbPoints) {

        List<PhotoPoint> points = new ArrayList<>();

        for(DBPhotoPoint point : dbPoints){
            points.add(mapSingle(point));
        }

        return points;
    }
}
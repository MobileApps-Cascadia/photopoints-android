package edu.cascadia.mobas.northcreekforest.repo;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import edu.cascadia.mobas.northcreekforest.data.PhotoPointsDatabase;
import edu.cascadia.mobas.northcreekforest.data.dto.DBPointItem;
import edu.cascadia.mobas.northcreekforest.model.Coordinates;
import edu.cascadia.mobas.northcreekforest.model.PointItem;

public class PointItemRepository implements IRepository<PointItem> {

    private final String TAG = "PointItemRepo";

    // repository data store
    private Context mContext;
    public PointItemRepository(Context context){
        mContext = context;
    }

    @Override
    public List<PointItem> getAll() {
        return map(PhotoPointsDatabase.getAppDatabase(mContext).pointItemDao().getAll());
    }

    @Override
    public Integer count(){
        return PhotoPointsDatabase.getAppDatabase(mContext).pointItemDao().getCount();
    }

    public PointItem getById(Integer id){
        try{
            return mapSingle(PhotoPointsDatabase.getAppDatabase(mContext).pointItemDao().getById(id));
        }
        catch(Exception ex){
            Log.d(TAG, ex.getMessage());
            return null;
        }
    }

    public Integer getIDByQRCode(String qrCode){
        try{
            Integer id = PhotoPointsDatabase.getAppDatabase(mContext).pointItemDao().getIDByQRCode(qrCode);
            return id == null ? 0 : id;
        }
        catch(Exception ex){
            Log.d(TAG, ex.getMessage());
            return 0;
        }
    }

    private PointItem mapSingle(DBPointItem point){
        if(point == null){
            return null;
        }

        return new PointItem(
                point.getId(),
                point.getType(),
                new Coordinates(point.getLatitude(), point.getLongitude()),
                point.getQrCode(),
                point.isInactive());
    }

    //TODO: Investigate mapper libraries that can help us remove this boilerplate code.
    //Mapper to return a list of PhotoPoints mapped from the DBModel.
    private List<PointItem> map(List<DBPointItem> dbPoints) {

        List<PointItem> points = new ArrayList<>();

        for(DBPointItem point : dbPoints){
            points.add(mapSingle(point));
        }

        return points;
    }
}
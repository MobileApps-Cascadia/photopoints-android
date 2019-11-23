package edu.cascadia.mobas.photopoints.repo;

import java.util.ArrayList;
import java.util.List;

import edu.cascadia.mobas.photopoints.model.PhotoPoint;

public class PhotoPointsRepository implements Repository<PhotoPoint> {

    private static ArrayList<PhotoPoint> mPhotoPoints = new ArrayList<PhotoPoint>(){{
        add(new PhotoPoint("Plant0001", 47.776013, -122.192043, PhotoPoint.PhotoPointType.Plant));
        add(new PhotoPoint("Plant0002", 47.775886, -122.192635, PhotoPoint.PhotoPointType.Plant));
        add(new PhotoPoint("Plant0003", 47.776013, -122.193909, PhotoPoint.PhotoPointType.Plant));
        add(new PhotoPoint("Plant0004", 47.775241, -122.195866, PhotoPoint.PhotoPointType.Plant));
        add(new PhotoPoint("Plant0005", 47.774999, -122.195243, PhotoPoint.PhotoPointType.Plant));
        add(new PhotoPoint("Plant0006", 47.774484, -122.195694, PhotoPoint.PhotoPointType.Plant));
        add(new PhotoPoint("Plant0007", 47.773701, -122.194359, PhotoPoint.PhotoPointType.Plant));
        add(new PhotoPoint("Plant0008", 47.774150, -122.192456, PhotoPoint.PhotoPointType.Plant));
        add(new PhotoPoint("Plant0009", 47.775886, -122.192635, PhotoPoint.PhotoPointType.Plant));
    }};

    @Override
    public List<PhotoPoint> getAll() {
        return mPhotoPoints;
    }

    public int count(){
        return mPhotoPoints.size();
    }
}
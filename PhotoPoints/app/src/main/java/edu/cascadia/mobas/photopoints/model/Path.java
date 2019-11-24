package edu.cascadia.mobas.photopoints.model;

import androidx.annotation.NonNull;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.ArrayList;
import java.util.List;
import static edu.cascadia.mobas.photopoints.R.color.*;
import edu.cascadia.mobas.photopoints.R;

public class Path {

    public enum PathType {
        UNKNOWN_TYPE,       // 0 - undefined path type
        CREEK,              // 1 - path is a creek
        TRAIL,              // 2 - path is a regular trail
        MINOR_TRAIL         // 3 - path is a "hidden" trail, does not resemble a trail (required for some photopoints)
    }

    private ArrayList<GeoCoordinate> mGeo;
    private PathType mPathType = PathType.UNKNOWN_TYPE;
    private String mLabel = "";
    private int lineColor=0;

    // Constructor chain

    public Path() {
        mGeo = new ArrayList<GeoCoordinate>();
    }

    public Path(PathType type) {
        this();
        mPathType = type;
    }

    public Path(String label) {
        this();
        mLabel = label;
    }

    public Path(@NonNull List<GeoCoordinate> geos) {
        this();
        mGeo.addAll(geos);
    }

    public Path(String label, PathType type) {
        this(type);
        mLabel = label;
    }

    public Path(@NonNull List<GeoCoordinate> geos, PathType type) {
        this(geos);
        mPathType = type;
    }

    public Path(String label, @NonNull List<GeoCoordinate> geos, PathType type) {
        this(geos, type);
        mLabel = label;
    }

    // extends path with a single coordinate
    public void addToPath(@NonNull GeoCoordinate geo) {
        if (geo.isValid()) mGeo.add(geo);
    }

    // Adds a list of GeoCoordinates to the path
    public void addToPath(@NonNull List<GeoCoordinate> geos) {

        mGeo.addAll(geos);
    }

    // Return Path as ArrayList of LatLng items
    public List<LatLng> getLatLngList() {
        ArrayList<LatLng> latLngList = new ArrayList<>();
        for (GeoCoordinate geo : mGeo) {
            latLngList.add(new LatLng(geo.getLatitude(), geo.getLongitude()));
        }
        return latLngList;
    }

    // Returns a copy of the Geocoordinates List
    public List<GeoCoordinate> getGeoCoordinates() {
        ArrayList<GeoCoordinate> geoList = new ArrayList<GeoCoordinate>();
        for (GeoCoordinate geo : mGeo) {
            geoList.add(new GeoCoordinate(geo.getLatitude(), geo.getLongitude()));
        }
        return geoList;
    }

    // Return integer color value for path display on map
    private int getLineColor() {
        int color;
        switch (this.mPathType) {
            /*
            case CREEK: color = 11197183; break;
            case TRAIL: color = 16773807; break;
            case MINOR_TRAIL: color = 16773807; break;
            default: color = 16773807;
*/

            case CREEK: color = edu.cascadia.mobas.photopoints.R.color.ColorPathCreek; break;
            case TRAIL: color = edu.cascadia.mobas.photopoints.R.color.ColorPathTrail; break;
            case MINOR_TRAIL: color = edu.cascadia.mobas.photopoints.R.color.ColorPathMinorTrail; break;
            default: color = edu.cascadia.mobas.photopoints.R.color.ColorPathDefault; break;
        }
        //return color;
        return -400201;   // no context available to access any information in res, always black
    }

    // Return line pattern for for path display on map
    private List<PatternItem> getLinePattern() {
        switch(this.mPathType) {
            case MINOR_TRAIL:
                ArrayList<PatternItem> pattern = new ArrayList<>();
                pattern.add(new Dash(5));
                pattern.add(new Gap(3));
                return pattern;
            default: return null;  // default is solid line;
        }
    }

    // returns path as Google Maps polyline
    public PolylineOptions getPolylineOptions () {
        return new PolylineOptions()
                .addAll(getLatLngList())
                .jointType(JointType.ROUND)
                .color(getLineColor())
                .pattern(getLinePattern());
    }
}
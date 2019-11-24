package edu.cascadia.mobas.photopoints.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.List;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.PolylineOptions;

import edu.cascadia.mobas.photopoints.R;

public class Path {

    public enum PathType {
        UNKNOWN_TYPE,       // 0 - undefined path type
        CREEK,              // 1 - path is a creek
        TRAIL,              // 2 - path is a regular trail
        MINOR_TRAIL         // 3 - path is a "hidden" trail, does not resemble a trail (required for some photopoints)
    }

    private ArrayList<GeoCoordinate> mGeo;    // sequential list of coordinates
    private PathType mPathType;               // enumerated path type
    private String mLabel = "";               // label for path

    // Constructor chain
    // Path(String pathId, String label, List<GeoCoordinates> geos, PathType type)

    public Path() { mGeo = new ArrayList<GeoCoordinate>(); mPathType = PathType.UNKNOWN_TYPE; }

    public Path(PathType type) { this(); mPathType = type; }

    public Path(@NonNull String label) {  this(); mLabel = label; }

    public Path(@NonNull List<GeoCoordinate> geos) { this(); mGeo.addAll(geos); }

    public Path(@NonNull String label, PathType type) { this(type); mLabel = label; }

    public Path(@NonNull List<GeoCoordinate> geos, PathType type) { this(geos); mPathType = type; }

    public Path(@NonNull String label, @NonNull List<GeoCoordinate> geos, PathType type) {
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
            latLngList.add(new LatLng(geo.latitude(), geo.longitude()));
        }
        return latLngList;
    }


    // Returns a reference of the Geocoordinates List
    public List<GeoCoordinate> getGeoCoordinates() {
        return mGeo;
    }


    // Return integer color value for path display on map
    private int getLineColor(Context context) {
        int color;
        switch (this.mPathType) {
            case CREEK: color = ContextCompat.getColor(context, R.color.ColorPathCreek); break;
            case TRAIL: color = ContextCompat.getColor(context, R.color.ColorPathTrail); break;
            case MINOR_TRAIL: color = ContextCompat.getColor(context, R.color.ColorPathMinorTrail); break;
            default: color = ContextCompat.getColor(context, R.color.ColorPathDefault); break;
        }
        return color;
    }


    // Return a line pattern for for path display on map
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
    public PolylineOptions getPolylineOptions (Context context) {
        return new PolylineOptions()
                .addAll(getLatLngList())
                .jointType(JointType.ROUND)
                .pattern(getLinePattern())
                .color(getLineColor(context)
                );
    }
}
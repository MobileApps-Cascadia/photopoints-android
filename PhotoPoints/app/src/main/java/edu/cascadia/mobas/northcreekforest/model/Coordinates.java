package edu.cascadia.mobas.northcreekforest.model;


// a POJO geocoordinate class

import androidx.annotation.NonNull;

public class Coordinates {
    private double longitude;
    private double latitude;
    private double altitude;

    public Coordinates(double latitude, double longitude, double altitude) {
        setLongitude(longitude);
        setLatitude(latitude);
        setAltitude(longitude);
    }

    public Coordinates(double lat, double lng) {
        this(lat, lng, 0);
    }

    public Coordinates(@NonNull String coords) {
        String [] coordSet = coords.trim().split(",", 4);
        double lng = Double.parseDouble(coordSet[0]);
        double lat = Double.parseDouble(coordSet[1]);
        double alt = (coordSet.length < 3 ? 0 : Double.parseDouble(coordSet[2]));

        // swap coordinates if order given is longitude,latitude (based on North America)
        if (lat < 0 && lng > 0) {
            double temp = lat;
            lat = lng;
            lng = temp;
        }

        this.latitude = lat;
        this.longitude = lng;
        this.altitude = alt;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getAltitude() {
        return this.altitude;
    }

    public void setLongitude(double lng) {
        if (lng <= 0) {
            this.longitude = lng;
        } else {
            setLatitude(lng);
        }
    }

    public void setLatitude(double lat) {
        if (lat >= 0 ) {
            this.latitude = lat;
        } else {
            setLongitude(lat);
        }
    }

    public void setAltitude(double alt) {
        this.altitude = alt;
    }
}

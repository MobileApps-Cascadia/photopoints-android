package edu.cascadia.mobas.photopoints.model;


// a POJO geocoordinate class

public class Coordinates {
    private double longitude;
    private double latitude;
    private double altitude;

    Coordinates(double lat, double lng, double alt) {
        setLongitude(lng);
        setLatitude(lat);
        setAltitude(alt);
    }

    public Coordinates(double lat, double lng) {
        this(lat, lng, 0);
    }

    public Coordinates(GeoCoordinate geo) {
        this(geo.getLatitude(), geo.getLongitude(), geo.getAltitude());

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

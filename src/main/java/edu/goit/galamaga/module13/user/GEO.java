package edu.goit.galamaga.module13.user;

import java.util.Objects;

public class GEO {

    private double lat;
    private double lng;

    public GEO() {
        this(0.0, 0.0);
    }

    public GEO(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GEO)) return false;
        return this.lat == ((GEO) o).lat && this.lng == ((GEO) o).lng;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLat(), getLng());
    }

    @Override
    public String toString() {
        return "GEO{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}

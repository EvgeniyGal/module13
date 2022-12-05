package edu.goit.galamaga.module13.user;

import java.util.Objects;

public class Address {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GEO geo;

    public Address(String street, String suite, String city, String zipcode, GEO geo) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geo = geo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        return this.city.equals(((Address) o).city) && this.street.equals(((Address) o).street) &&
                this.suite.equals(((Address) o).suite) && this.zipcode.equals(((Address) o).zipcode) &&
                this.geo.equals(((Address) o).geo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStreet(), getSuite(), getCity(), getZipcode(), getGeo().hashCode());
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", suite='" + suite + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", geo=" + geo +
                '}';
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public GEO getGeo() {
        return geo;
    }

    public void setGeo(GEO geo) {
        this.geo = geo;
    }
}

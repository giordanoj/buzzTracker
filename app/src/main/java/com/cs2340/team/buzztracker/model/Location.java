package com.cs2340.team.buzztracker.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Location {

    /** the location's id number */
    private int _id;

    /** the location's name */
    private String _name;

    /** the location's latitude */
    private String _latitude;

    /** the location's longitude */
    private String _longitude;

    /** the location's street address*/
    private String _streetAddress;

    /** the location's city*/
    private String _city;

    /** the location's state*/
    private String _state;

    /** the location's zip code*/
    private String _zip;

    /** the location's type*/
    private String _type;

    /** the location's phone number*/
    private String _phone;

    /** the location's website*/
    private String _website;


    /* **********************
     * Getters and setters
     */

    //no setter for this.  id is a read only field
    public int getId() { return _id; }

    public String get_Name() { return _name; }
    public void set_Name(String name) { _name = name; }

    public String get_Latitude() { return _latitude; }
    public void set_Latitude(String latitude) { _latitude = latitude; }

    public String get_Longitude() { return _longitude; }
    public void set_Longitude(String longitude) { _longitude = longitude; }

    public String get_Address() { return _streetAddress; }
    public void set_Address(String streetAddress) { _streetAddress = streetAddress; }

    public String get_City() { return _city; }
    public void set_City(String city) { _city = city; }

    public String get_State() { return _state; }
    public void set_State(String state) { _state = state; }

    public String get_Zip() { return _zip; }
    public void set_Zip(String zip) { _zip = zip; }

    public String get_Type() { return _type; }
    public void set_Type(String type) { _type = type; }

    public String get_Phone() { return _phone; }
    public void set_Phone(String phone) { _phone = phone; }

    public String get_Website() { return _website; }
    public void set_Website(String website) { _website = website; }

    /**
     * Make a new Location
     * @param id            the Location's id number
     * @param name          the Location's name
     * @param latitude      the Location's latitude
     * @param longitude     the Location's longitude
     * @param address       the Location's street address
     * @param city          the Location's city
     * @param state         the Location's state
     * @param zip           the Location's zip code
     * @param type          the Location's type
     * @param phone         the Location's phone number
     * @param website       the Location's website
     */
    public Location(int id, String name, String latitude, String longitude, String address, String city, String state, String zip, String type, String phone, String website) {
        _id = id;
        _name = name;
        _latitude = latitude;
        _longitude = longitude;
        _streetAddress = address;
        _city = city;
        _state = state;
        _zip = zip;
        _type = type;
        _phone = phone;
        _website = website;
    }

    @Override
    public boolean equals(Object c) {
        Location o = (Location) c;
        return (o.get_Name().equals(_name) && o.get_Latitude().equals(_latitude) && o.get_Longitude().equals(_longitude)
                && o.get_Address().equals(_streetAddress) && o.get_City().equals(_city) && o.get_State().equals(_state)
                && o.get_Zip().equals(_zip) && o.get_Type().equals(_type) && o.get_Phone().equals(_phone)
                && o.get_Website().equals(_website));
    }

    @Override
    public String toString() {
        return _id + " " + _name + " " + _latitude + " " + _longitude + " " + _streetAddress + " " +
                _city + " " + _state + " " + _zip + " " + _type + " " + _phone + " " + _website;
    }

}

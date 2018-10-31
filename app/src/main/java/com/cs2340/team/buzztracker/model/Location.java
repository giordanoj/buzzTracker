package com.cs2340.team.buzztracker.model;

public class Location {

    /** this is the id for a location */
    private int _id;

    /** this is the name of the location */
    private String _name;

    /** this is the locations gps coordinates should only contain 2 floats*/
    private float[] _coordinates;

    /**this is the location's street address */
    private String _address;

    /**this is the location's city */
    private String _city;

    /**this is the location's state */
    private String _state;

    /**this is the location's zip code */
    private String _zip;

    /** this is the type of the location */
    private String _type;

    /** this is a phone number for a location */
    private String _phoneNumber;

    /** this is a web link to the location */
    private String _website;

    /** this is the inventory for a location */
    private Inventory _inventory;

    /** this is the graphs for a particular location */
    private Graph _graph;

    /*
     *  getters and setters
     */

    public int get_id() {return _id; }

    public String get_name() {
        return _name;
    }
    public void set_name(String _name) {
        this._name = _name;
    }

    public float[] get_coordinates() {
        return _coordinates;
    }
    public void set_coordinates(float[] _coordinates) {
        this._coordinates = _coordinates;
    }

    public String get_address() { return _address; }
    public void set_address(String _address) {this._address = _address; }

    public String get_city() {
        return _city;
    }
    public void set_city(String _city) {this._city = _city; }

    public String get_state() {
        return _state;
    }
    public void set_state(String _state) {this._state= _state; }

    public String get_zip() {
        return _zip;
    }
    public void set_zip(String _zip) {this._zip = _zip; }

    public String get_type() {
        return _type;
    }
    public void set_type(String _type) {
        this._type = _type;
    }

    public String get_phoneNumber() {
        return _phoneNumber;
    }
    public void set_phoneNumber(String _phoneNumber) {
        this._phoneNumber = _phoneNumber;
    }

    public String get_website() {
        return _website;
    }
    public void set_website(String _website) {
        this._website = _website;
    }

    public Inventory get_inventory() {
        return _inventory;
    }
    public void set_inventory(Inventory _inventory) {
        this._inventory = _inventory;
    }

    public Graph get_graph() {
        return _graph;
    }
    public void set_graph(Graph _graph) {
        this._graph = _graph;
    }

    /**
     * Make a new Location
     *
     * @param _id               The Location's id number
     * @param _name             The Location's name
     * @param _coordinates      The Location's latitude and longitude
     * @param _address          The Location's street address
     * @param _city             The Location's city
     * @param _state            The Location's state
     * @param _zip              The Location's zip code
     * @param _type             The Location's type
     * @param _phoneNumber      The Location's phone number
     * @param _website          The Location's web link
     * @param _inventory        The Location's inventory
     * @param _graph            The Location's graphs
     */
    public Location(int _id, String _name, float[] _coordinates, String _address, String _city,
                    String _state, String _zip, String _type, String _phoneNumber, String _website,
                    Inventory _inventory, Graph _graph) {
        this._id = _id;
        this._name = _name;
        this._coordinates = _coordinates;
        this._address = _address;
        this._city = _city;
        this._state = _state;
        this._zip = _zip;
        this._type = _type;
        this._phoneNumber = _phoneNumber;
        this._website = _website;
        this._inventory = _inventory;
        this._graph = _graph;
    }

    @Override
    public boolean equals(Object c) {
        Location o = (Location) c;
        return (o.get_name().equals(_name) && o.get_coordinates().equals(_coordinates));
    }

    @Override
    public String toString() {
        return _id + ": " +_name  ;
    }
}

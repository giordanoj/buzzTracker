package com.cs2340.team.buzztracker.model;

public class Location {

    /** this is the name of the location */
    private String _name;

    /** this is the locations gps coordinates should only contain 2 floats*/
    private float[] _coordinates;

    /** this is a web link to the location */
    private String _webLink;

    /** this is the id for a location */
    private int _id;

    /** this is a phone number for a location */
    private String _phoneNumber;

    /** this is the type of the location */
    private String _type;

    /** this is the inventory for a location */
    private Inventory _inventory;

    /** this is the graphs for a particular location */
    private Graph _graph;


    /*
     *  getters and setters
     */

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

    public String get_webLink() {
        return _webLink;
    }

    public void set_webLink(String _webLink) {
        this._webLink = _webLink;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_phoneNumber() {
        return _phoneNumber;
    }

    public void set_phoneNumber(String _phoneNumber) {
        this._phoneNumber = _phoneNumber;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
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


}

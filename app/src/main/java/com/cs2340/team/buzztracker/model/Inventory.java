package com.cs2340.team.buzztracker.model;

import java.util.ArrayList;

public class Inventory {


    /** this is an Arraylist to hold the items in a particular iventory
     * this should only hold around a hundred or so for the user
     * then refreshing when more is needed
     */
    private ArrayList<Item> _items;

    /** holds the Location of an inventory */
    private Location _location;


    /*
        getters and setters
     */

    public ArrayList<Item> get_items() {
        return _items;
    }

    public void set_items(ArrayList<Item> _items) {
        this._items = _items;
    }

    public Location get_location() {
        return _location;
    }

    public void set_location(Location _location) {
        this._location = _location;
    }
}

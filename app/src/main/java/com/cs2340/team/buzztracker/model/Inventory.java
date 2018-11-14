package com.cs2340.team.buzztracker.model;

import java.util.ArrayList;

/**
 * class for the information of an inventory
 */
public class Inventory {


    /** this is an ArrayList to hold the items in a particular inventory
     * this should only hold around a hundred or so for the user
     * then refreshing when more is needed
     */
    private ArrayList<Item> _items;

    /** holds the Location of an inventory */
    private Location _location;


    /*
        getters and setters
     */

    /**
     *
     * @return the times at a particular inventory
     */
    public ArrayList<Item> get_items() {
        return _items;
    }

    /**
     * this will likely never be used
     * @param _items the arrayList of items to set at an inventory
     */
    public void set_items(ArrayList<Item> _items) {
        this._items = _items;
    }

    /**
     *
     * @return the location of an inventory
     */
    public Location get_location() {
        return _location;
    }

    /**
     *
     * @param _location set the location of an inventory to param
     */
    public void set_location(Location _location) {
        this._location = _location;
    }

    /**
     * make a new Inventory
     *
     * @param _items            the collection of items at an Inventory
     * @param _location         The location of an inventory
     */
    public Inventory(ArrayList<Item> _items, Location _location) {
        this._items = _items;
        this._location = _location;
    }

    /**
     *
     * @param item to add at a location
     * @return whether the operation succeeded or not
     */
    public boolean addItem(Item item) {
        if (item != null) {
            for (Item i : _items) {
                if (i.equals(item)) {
                    return false;
                }
            }
            _items.add(item);
            return true;
        }
        return false;
    }
}

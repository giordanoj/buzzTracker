package com.cs2340.team.buzztracker.model;

import java.util.ArrayList;

/**
 * the main model class for all things the application does
 */
public final class Model {
    /** Singleton instance */
    private static final Model _instance = new Model();

    /**
     *
     * @return an instance of teh model
     */
    public static Model getInstance() { return _instance; }

    /** holds the list of all courses */
    private ArrayList<Location> _locations;

    /** hold the list of all item categories */
    private ArrayList<String> _categories;

    /** the inventory of the location currently being viewed
     *  will not be set until the user requests to see a particular location's inventory
     */
    private Inventory _currentInventory;

    private Location _currentLocation;

    private User _currentUser;

    private Item _currentItem;

    /** Null Location pattern, returned when no location is found */
    public final Location theNullLocation = new Location(0, "No such location",
            null, "", "", "", "","","",
            "", null, null);

    /** Null User pattern, returned when no user is found */
    public final User theNullUser = new User(0, "No such user", "",
            "", false, null, null);

    /** Null Inventory pattern, returned when no inventory is found */
    public final Inventory theNullInventory = new Inventory(null, null);

    /** Null Item pattern, returned when no item is found */
    public final Item theNullItem = new Item(0, "", "", "", ""
            , "", "No such item.", "", 0, "",
            0, 0, 0);

    /**
     * make a new model
     */
    private Model() {
        _locations = new ArrayList<>();
        _categories = new ArrayList<>();

        setCurrentLocation(theNullLocation);
        setCurrentInventory(theNullInventory);
        setCurrentItem(theNullItem);
    }

    /**
     *
     * @return an arraylist of all locations
     */
    public ArrayList<Location> getLocations() { return _locations; }

    /**
     *
     * @param location the location to be added to the device
     * @return whether the operation succeeded
     */
    public boolean addLocation(Location location) {
        if (location != null) {
            for (Location l : _locations ) {
                if (l.equals(location)) return false;
            }
            _locations.add(location);
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param id the id of the location we want
     * @return the location we are looking for, or null if not found
     */
    public Location getLocationById(int id) {
        for (Location l : _locations) {
            if (l.get_id() == id) {
                return l;
            }
        }
        return null;
    }

    /**
     *
     * @return the categories items can be sorted into
     */
    public ArrayList<String> getCategories() { return _categories; }

    /**
     *
     * @param category the category to add
     * @return whether the addition succeeded or not
     */
    public boolean addCategory(String category) {
        for (String c : _categories) {
            if (c.equals(category)) return false;
        }
        _categories.add(category);
        return true;
    }

    /**
     *
     * @return the current location
     */
    public Location getCurrentLocation() { return _currentLocation;}

    /**
     *
     * @param location the location we want the device to see
     */
    public void setCurrentLocation(Location location) { _currentLocation = location; }

    /**
     *
     * @return the current user profile we are using
     */
    public User getCurrentUser() { return _currentUser;}

    /**
     *
     * @param user the user we want the application to use for verfication
     */
    public void setCurrentUser(User user) { _currentUser = user; }

    /**
     *
     * @return the current inventory
     */
    public Inventory getCurrentInventory() { return _currentInventory;}

    /**
     *
     * @param inventory the inventory to change to
     */
    public void setCurrentInventory(Inventory inventory) { _currentInventory = inventory; }

    /**
     *
     * @return the current item
     */
    public Item getCurrentItem() { return _currentItem;}

    /**
     *
     * @param item the item we want to change to
     */
    public void setCurrentItem(Item item) { _currentItem = item; }

    /**
     * clears model, for unit testing only
     */
    public void resetModel() {
        _locations = new ArrayList<>();
        _categories = new ArrayList<>();

        setCurrentLocation(theNullLocation);
        setCurrentInventory(theNullInventory);
        setCurrentItem(theNullItem);
    }
}

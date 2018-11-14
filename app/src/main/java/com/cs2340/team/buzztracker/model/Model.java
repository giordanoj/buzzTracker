package com.cs2340.team.buzztracker.model;

import java.util.ArrayList;


public final class Model {
    /** Singleton instance */
    private static final Model _instance = new Model();
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

    public ArrayList<Location> getLocations() { return _locations; }

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

    public Location getLocationById(int id) {
        for (Location l : _locations) {
            if (l.get_id() == id) {
                return l;
            }
        }
        return null;
    }

    public ArrayList<String> getCategories() { return _categories; }

    public boolean addCategory(String category) {
        for (String c : _categories) {
            if (c.equals(category)) return false;
        }
        _categories.add(category);
        return true;
    }

    public Location getCurrentLocation() { return _currentLocation;}

    public void setCurrentLocation(Location location) { _currentLocation = location; }

    public User getCurrentUser() { return _currentUser;}

    public void setCurrentUser(User user) { _currentUser = user; }

    public Inventory getCurrentInventory() { return _currentInventory;}

    public void setCurrentInventory(Inventory inventory) { _currentInventory = inventory; }

    public Item getCurrentItem() { return _currentItem;}

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

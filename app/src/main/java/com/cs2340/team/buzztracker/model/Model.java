package com.cs2340.team.buzztracker.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    /** Singleton instance */
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    /** holds the list of all courses */
    private ArrayList<Location> _locations;

    private Location _currentLocation;

    /** Null Location pattern, returned when no location is found */
    public final Location theNullLocation = new Location(0, "No Such Location", "", "", "","","","","","","");

    /**
     * make a new model
     */
    private Model() {
        _locations = new ArrayList<>();

        setCurrentLocation(theNullLocation);
    }

    public ArrayList<Location> getLocations() { return _locations; }

    public boolean addLocation(Location location) {
        for (Location l : _locations ) {
            if (l.equals(location)) return false;
        }
        _locations.add(location);
        return true;
    }

    public Location getCurrentLocation() { return _currentLocation;}

    public void setCurrentLocation(Location location) { _currentLocation = location; }
}

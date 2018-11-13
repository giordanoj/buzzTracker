package com.cs2340.team.buzztracker;

import com.cs2340.team.buzztracker.model.Graph;
import com.cs2340.team.buzztracker.model.Inventory;
import com.cs2340.team.buzztracker.model.Location;
import com.cs2340.team.buzztracker.model.Model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class ModelTest {

    private ArrayList<Location> locationArray;

    private Model model = Model.getInstance();
    float[] coordinate;
    Inventory invent;

    @Before
    public void setUp() {

        model.resetModel();
        locationArray = new ArrayList<>();
        coordinate = new float[2];
        invent = new Inventory(null, null);

        Location one = new Location(125, "Georgia-0", coordinate, "1551 WestVeillag Street", "Atlanta",
                "Georgia", "71220", "Department", "678-549-9562", "www.goodwill.com",
                invent, new Graph());

        Location two = new Location(126, "Georgia-1", coordinate, "1551 WestVeillag Street", "Atlanta",
                "Georgia", "71220", "Department", "678-549-9562", "www.goodwill.com",
                invent, new Graph());

        Location three = new Location(127, "Georgia-2", coordinate, "1551 WestVeillag Street", "Atlanta",
                "Georgia", "71220", "Department", "678-549-9562", "www.goodwill.com",
                invent, new Graph());

        Location four = new Location(128, "Georgia-3", coordinate, "1551 WestVeillag Street", "Atlanta",
                "Georgia", "71220", "Department", "678-549-9562", "www.goodwill.com",
                invent, new Graph());

        Location five = new Location(129, "Georgia-4", coordinate, "1551 WestVeillag Street", "Atlanta",
                "Georgia", "71220", "Department", "678-549-9562", "www.goodwill.com",
                invent, new Graph());


        Location six = new Location(130, "Georgia-5", coordinate, "1551 WestVeillag Street", "Atlanta",
                "Georgia", "71220", "Department", "678-549-9562", "www.goodwill.com",
                invent, new Graph());

        locationArray.add(one);
        locationArray.add(two);
        locationArray.add(three);
        locationArray.add(four);
        locationArray.add(five);
        locationArray.add(six);
    }

    @Test
    public void testAddLocation() {

        for (int i = 0; i < locationArray.size(); i++) {
            boolean added = model.addLocation(locationArray.get(i));
            assertTrue("Add locations does not return true/", added);
        }

        for (int i = 0; i < locationArray.size(); i++) {
            Location check = model.getLocationById(i + 125);
            assertEquals(check, locationArray.get(i));
        }
    }

    @Test
    public void testAddLocationDuplicate() {
        boolean added = model.addLocation(locationArray.get(0));
        assertFalse("Add duplicate location does not return false.", false);
    }

    @Test
    public void testAddLocationNull() {
        boolean added = model.addLocation(null);
        assertFalse("Add null location does not return false.", false);
    }

    @Test
    public void testAddLocationSameNameDifferentCoordinate() {
        float[] coordinate = new float[2];
        coordinate[0] = 1.44f;
        coordinate[1] = 1.44f;
        Inventory invent = new Inventory(null, null);
        Location one = new Location(155, "Georgia-0", coordinate, "1551 WestVeillag Street", "Atlanta",
                "Georgia", "71220", "Department", "678-549-9562", "www.goodwill.com",
                invent, new Graph());
        boolean added = model.addLocation(one);
        assertTrue("Add same location does not return true.", added);
    }

    @Test
    public void testAddLocationSameCoordinateDifferentName() {
        float[] coordinate = new float[2];
        Inventory invent = new Inventory(null, null);
        Location one = new Location(190, "Georgia-10", coordinate, "1551 WestVeillag Street", "Atlanta",
                "Georgia", "71220", "Department", "678-549-9562", "www.goodwill.com",
                invent, new Graph());
        boolean added = model.addLocation(one);
        assertTrue("Add same location does not return true.", added);
    }

    @Test
    public void testGetLocationById() {
        model.addLocation(locationArray.get(0));

        Location check = model.getLocationById(125);
        assertEquals(check, locationArray.get(0));
    }

    @Test
    public void testGetLocationBadId() {
        model.addLocation(locationArray.get(0));
        assertNull(model.getLocationById(126));
    }
}

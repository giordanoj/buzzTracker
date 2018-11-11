package com.cs2340.team.buzztracker;

import com.cs2340.team.buzztracker.model.Graph;
import com.cs2340.team.buzztracker.model.Inventory;
import com.cs2340.team.buzztracker.model.Location;
import com.cs2340.team.buzztracker.model.Model;


import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private Location[] locationArray =  (Location[]) new Object[6];

    final Model model = Model.getInstance();

    @Before
    public void setUp() {


        List<Location> locationList = new ArrayList<>();
        float [] coordinate = new float[2];
        Inventory invent = new Inventory(null, null);

        Location one = new Location( 125, "Georgia" , coordinate, "1551 WestVeillag Street", "Atlanta",
                "Georgia", "71220" , "Department", "678-549-9562", "www.goodwill.com",
                invent, new Graph());

        Location two = new Location( 126, "Georgia" , coordinate, "1551 WestVeillag Street", "Atlanta",
                "Georgia", "71220" , "Department", "678-549-9562", "www.goodwill.com",
                invent, new Graph());

        Location three = new Location( 127, "Georgia" , coordinate, "1551 WestVeillag Street", "Atlanta",
                "Georgia", "71220" , "Department", "678-549-9562", "www.goodwill.com",
                invent, new Graph());

        Location four = new Location( 128, "Georgia" , coordinate, "1551 WestVeillag Street", "Atlanta",
                "Georgia", "71220" , "Department", "678-549-9562", "www.goodwill.com",
                invent, new Graph());

        Location five = new Location( 129, "Georgia" , coordinate, "1551 WestVeillag Street", "Atlanta",
                "Georgia", "71220" , "Department", "678-549-9562", "www.goodwill.com",
                invent, new Graph());


        Location six = new Location( 130, "Georgia" , coordinate, "1551 WestVeillag Street", "Atlanta",
                "Georgia", "71220" , "Department", "678-549-9562", "www.goodwill.com",
                invent, new Graph());

        locationArray[0] = one;
        locationArray[1] = two;
        locationArray[2] = three;
        locationArray[3] = four;
        locationArray[4] = five;
        locationArray[5] = six;



    }


        @Test
        public void testAddLocation() {

            for (int i = 0; i < locationArray.length; i++) {
               boolean added = model.addLocation(locationArray[i]);
               assertEquals(added, true);
            }
            for (int i = 125; i < 131; i++) {
                Location found = model.getLocationById(i);
                assertEquals(found, locationArray[i - 125]);
            }
       }

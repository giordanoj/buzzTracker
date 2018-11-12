package com.cs2340.team.buzztracker;

import com.cs2340.team.buzztracker.model.Inventory;
import com.cs2340.team.buzztracker.model.Item;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class addItemUnitTest {
    private Item nullItem;
    private Item nonTrivialItem1;
    private Item nonTrivialItem2;
    private Inventory emptyInventory;
    private Inventory nonEmptyInventory;
    private ArrayList<Item> emptyItems = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();

    @Before
    public void setUp() {
        nullItem = null;
        nonTrivialItem1 = new Item(1, "Furniture", "9:00", "40.00",
                "chair.jpeg", "This is a chair. Looks beat up to me.",
                "This is a worn, wooden chair.", "This chair is " +
                "beaten up a little. It is wooden. Seems to have  yellow stain.", 50,
                "0:00", 41, 51, 6);
        nonTrivialItem2 = new Item(1, "Furniture", "10:00", "40.00",
                "chair2.jpeg", "This is a chair2. Looks beat up to me.",
                "This is a worn, wooden chair2.", "This chair2 is " +
                "beaten up a little. It is wooden. Seems to have red stain.", 50,
                "0:00", 42, 100, 6);

        items.add(nonTrivialItem1);
        emptyInventory = new Inventory(emptyItems, null);
        nonEmptyInventory = new Inventory(items, null);

    }

    @Test
    public void addNullItem() {
        assertFalse("Adding a null Item doesn't return false.", emptyInventory.addItem(nullItem));
    }

    @Test
    public void addNewItemToEmpty() {
        assertTrue("Adding a new Item to an empty inventory doesn't return True.",
                emptyInventory.addItem(nonTrivialItem1));
    }

    @Test
    public void addNewItemToNonEmpty() {
        assertTrue("Adding a new Item to an empty inventory doesn't return True.",
                nonEmptyInventory.addItem(nonTrivialItem2));
    }

    @Test
    public void addRepeatItem() {
        assertFalse("Adding a repeat Item doesn't return false.", nonEmptyInventory.addItem(nonTrivialItem1));
    }
    //Assert.assertTrue(message, condition)
    //Assert.assertEquals(message, expect, actual)
    // Assert.assertNull(message, variable)
    // Assert.assertSame(message, v1, v2)
    // Assert.assertNotSame(msg, v1, v2)
    // Assert.fail(message)
}
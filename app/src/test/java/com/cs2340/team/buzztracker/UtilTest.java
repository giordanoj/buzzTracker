package com.cs2340.team.buzztracker;

import com.cs2340.team.buzztracker.model.Item;
import com.cs2340.team.buzztracker.model.Model;
import com.cs2340.team.buzztracker.model.Util;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertSame;
import static org.junit.Assert.assertEquals;

public class UtilTest {
    private Model testModel;

    @Before
    public void setUp() {
        testModel = Model.getInstance();
    }
    @Test
    public void nullInput() {
        assertEquals(testModel.theNullItem, Util.parseItemString(null));
    }

    @Test
    public void emptyStringInput() {
        assertEquals(testModel.theNullItem, Util.parseItemString(" "));
    }

    @Test
    public void noTagsInput() {
        assertEquals(testModel.theNullItem, Util.parseItemString("Banana bread"));
    }

    @Test
    public void idBranch() {
        Item idItem = new Item(16,"","","","",
                "","","",0,
                "",0,0,0);
        assertEquals(idItem, Util.parseItemString("|Id:16|"));
    }

    @Test
    public void nameBranch() {
        Item nameItem = new Item(0,"","","","",
                "","Banana bread","",0,
                "",0,0,0);
        assertEquals(nameItem, Util.parseItemString("|Name:Banana bread|"));
    }

    @Test
    public void descriptionBranch() {
        Item descriptionItem = new Item(0,"","","","",
                "","","Bread with bananas in it.",0,
                "",0,0,0);
        assertEquals(descriptionItem, Util.parseItemString("|Description:Bread with bananas in it.|"));
    }

    @Test
    public void valueBranch() {
        Item valueItem = new Item(0,"","","5.00","",
                "","","",0,
                "",0,0,0);
        assertEquals(valueItem, Util.parseItemString("|Value:5.00|"));
    }

    @Test
    public void categoryBranch() {
        Item categoryItem = new Item(0,"Food","","","",
                "","","",0,
                "",0,0,0);
        assertEquals(categoryItem, Util.parseItemString("|Category:Food|"));
    }

    @Test
    public void donationTimeBranch() {
        Item donationTimeItem = new Item(0,"","Wed Oct 31 00:43:45 GMT 00:00 2018","","",
                "","","",0,
                "",0,0,0);
        assertEquals(donationTimeItem, Util.parseItemString("|Donation Time:Wed Oct 31 00:43:45 GMT 00:00 2018|"));
    }

    @Test
    public void saleTimeBranch() {
        Item saleTimeItem = new Item(0,"","","","",
                "","","",0,
                "Wed Oct 31 00:44:45 GMT 00:00 2018",0,0,0);
        assertEquals(saleTimeItem, Util.parseItemString("|Sale Time:Wed Oct 31 00:44:45 GMT 00:00 2018|"));
    }

    @Test
    public void enteredByBranch() {
        Item enteredByItem = new Item(0,"","","","",
                "","","",0,
                "",4,0,0);
        assertEquals(enteredByItem, Util.parseItemString("|Entered By:4|"));
    }

    @Test
    public void soldByBranch() {
        Item soldByItem = new Item(0,"","","","",
                "","","",0,
                "",0,4,0);
        assertEquals(soldByItem, Util.parseItemString("|Sold By:4|"));
    }

    @Test
    public void originBranch() {
        Item originItem = new Item(0,"","","","",
                "","","",20,
                "",0,0,0);
        assertEquals(originItem, Util.parseItemString("|Origin:20|"));
    }

    @Test
    public void pictureBranch() {
        Item pictureItem = new Item(0,"","","","nannerbread.jpg",
                "","","",0,
                "",0,0,0);
        assertEquals(pictureItem, Util.parseItemString("|Picture:nannerbread.jpg|"));
    }

    @Test
    public void commentBranch() {
        Item commentItem = new Item(0,"","","","",
                "This is the best darn bread ever!","","",0,
                "",0,0,0);
        assertEquals(commentItem, Util.parseItemString("|Comments:This is the best darn bread ever!|"));
    }

    @Test
    public void currentLocationBranch() {
        Item currentLocationItem = new Item(0,"","","","",
                "","","",0,
                "",0,0,66);
        assertEquals(currentLocationItem, Util.parseItemString("|Current Location:66|"));
    }

    @Test
    public void multipleTagsInput() {
        Item multipleItem = new Item(16,"Food","Wed Oct 31 00:43:45 GMT 00:00 2018","5.00","nannerbread.jpg",
                "This is the best darn bread ever!","Banana bread","Bread with bananas in it.",20,
                "Wed Oct 31 00:44:45 GMT 00:00 2018",4,4,66);
        assertEquals(multipleItem, Util.parseItemString("|Id:16~Name:Banana bread~Description:Bread with bananas in it.~Value:5.00~Category:Food~Donation Time:Wed Oct 31 00:43:45 GMT 00:00 2018~Sale Time:Wed Oct 31 00:44:45 GMT 00:00 2018~Entered By:4~Sold By:4~Origin:20~Picture:nannerbread.jpg~Comments:This is the best darn bread ever!~Current Location:66|"));
    }
}

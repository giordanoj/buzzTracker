package com.cs2340.team.buzztracker;


import com.cs2340.team.buzztracker.model.Model;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertEquals;

public class AddCategoryUnitTest {
    private Model testModel;
    private List<String> _categories;



    @Before
    public void setTestModel() {
        _categories = new ArrayList<>();
        testModel = Model.getInstance();
        testModel.resetModel();
    }

    @Test
    public void initiallyEmpty() {
        assertEquals( _categories, testModel.getCategories());
    }

    @Test
    public void addToEmpty() {
        assertEquals(true, testModel.addCategory("Test1"));
    }

    @Test
    public void addDuplicate() {
        testModel.addCategory("Test1");
        assertEquals(false, testModel.addCategory("Test1"));
    }

    @Test
    public void addToFilled() {
        testModel.addCategory("Test1");
        assertEquals(true, testModel.addCategory("Test2"));
    }

    @Test
    public void addToEmptyContents() {
        testModel.addCategory("Test1");
        assertEquals(true, testModel.getCategories().contains("Test1"));
    }

    @Test
    public void addDuplicateContents() {
        testModel.addCategory("Test5");
        _categories.add("Test5");
        assertEquals(_categories, testModel.getCategories());

    }

    @Test
    public void addMultipleContents() {
        testModel.addCategory("Test6");
        testModel.addCategory("Test7");
        _categories.add("Test6");
        _categories.add("Test7");
        assertEquals(_categories, testModel.getCategories());
    }

    @Test
    public void addEmptySize() {
        testModel.addCategory("Test42");
        assertEquals(1, testModel.getCategories().size());
    }

    @Test
    public void addDuplicateSize() {
        testModel.addCategory("Test1");
        testModel.addCategory("Test1");
        assertEquals(1, testModel.getCategories().size());
    }

    @Test
    public void addMultipleSize() {
        testModel.addCategory("Test3");
        testModel.addCategory("Test4");
        assertEquals(2, testModel.getCategories().size());
    }



}

package com.cs2340.team.buzztracker.model;

/*
    this is an enum class to contain the categories for each category type
    can add any more in here as see fit
    removing may have unintended consequences with database
    sorted alphabetically except all categories which should be on top
 */

public enum CategoryTypes {
    AllCategories("All Categories"),
    Books("Books"),
    Clothing("Clothing"),
    Electronics("Electronics"),
    Home("Home"),
    PetSupplies("Pet Supplies"),
    Sports("Sports and Outdoors");

    //stores the string seen in the parens above
    private final String var;

    CategoryTypes(String value) {

        this.var = value;
    }

    /**
     *
     * @return the value associated with a specific enum
     */
    public String getCategoryType() {return var;}

    /**
     *
     * @return the display string of an enum
     */
    @Override
    public String toString() {return var;}








}

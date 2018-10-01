package com.cs2340.team.buzztracker.model;

public enum  UserTypes {
    User("User"),
    Admin("Admin"),
    Location_Employee("Location Employee"),
    Manager("Manager");


    private final String var;

    UserTypes(String value) {

        this.var = value;
    }

    /**
     *
     * @return the  User Type
     */
    public String getUserType() { return var; }

    /**
     *
     * @return the display string representation of the User Type
     */
    @Override
    public String toString() { return var; }


}

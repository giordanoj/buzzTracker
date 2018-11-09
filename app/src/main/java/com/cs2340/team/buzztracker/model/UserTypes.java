package com.cs2340.team.buzztracker.model;

/**
Class represent the Types of Users

*/

public enum  UserTypes {
    /**
     * Type of User: Regular User
     */
    User("User"),
    /**
     * Type of User: Admin
     */
    Admin("Admin"),
    /**
     * Type of User: Location Employee
     */
    Location_Employee("Location Employee"),
    /**
     * Type of User: Manager
     */
    Manager("Manager");


    private final String var;

    /**
     * 
     * @param String value representing the User Type
     */

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

package com.cs2340.team.buzztracker.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


    /**
     * Represents a User Class Object
     *
     */

    public class User implements Parcelable {

        /** this user id number */
        private final int _id;

        /** this user's Name */
        private String _name;

        /**this is the user's email */
        private String _email;

        /** this user's password */
        private String _password;

        /**This stores if the users account is locked*/
        private Boolean _locked;

        /** this user type */
        private UserTypes _userType;

        /**this is the user's location*/
        private Location _location;


        /**this is the user's location*/
        private boolean _googleUser;


        /* **********************
         * Getters and setters
         */

        //no setter for this.  id is a read only field

        /**
         * getter for the if sign in by Google
         * @return boolean _googleUser
         */
        public boolean getGoogleUser() { return _googleUser; }


        /**
         * setter for the if sign in by Google
         *
         */
        public void setGoogleUser(boolean googleUser) {  _googleUser = googleUser; }

        /**
         * getter for the User ID
         * @return int id
         */
        public int getId() { return _id; }

        /**
         * getter for the User name
         * @return String _name
         */

        public String get_Name() { return _name; }

        /**
         * setter for the User Name
         * @param name to be set
         */
        public void set_Name(String name) { _name = name; }

        /**
         * getter for the User Email
         * @return the email of the user
         */

        public String get_Email() { return _email; }

        /**
         * setter for the User Email
         * @param email the email to be set
         */
        public void set_Email(String email) { _email = email; }

        /**
         * getter for User's Password
         * @return String the password of the user
         */
        public String get_Password() { return _password; }

        /**
         * setter for the User's Password
         * @param password the String password to be set
         */
        public void set_Password(String password) { _password = password ; }

        /**
         * getter if the account is locked
         * @return boolean of whether or not account will be lock
         */
        public Boolean get_locked() { return _locked; }

        /**
         * setter for account lock
         * @param locked set the account to lock
         */
        public void set_Locked (Boolean locked) { _locked = locked; }

        /**
         * getter for the User Type
         * @return UserTypes of the User
         */
        public UserTypes getUserType() {return _userType; }

        /**
         * setter for User Type
         * @param type the UserType of the User
         */
        public void setUserType(UserTypes type) { _userType = type; }

        /**
         * getter for the Location
         * @return the Location
         */
        public Location get_Location() { return _location; }

        /**
         * setter for the Location
         * @param location the location to be set
         */
        public void set_location(Location location) { _location = location; }


        /**
         * Make a new User; this constructor specifies a location to be used for Location Employees
         * @param id            the User's id
         * @param name          the User's name
         * @param email         the User's email
         * @param password      the User's password
         * @param locked        the User's account status
         * @param userType      the User's account type
         * @param location      the User's home location (only applies to Users of type
         *                      Location Employee
         *
         */
        public User(int id, String name, String email, String password, boolean locked,
                    UserTypes userType, Location location) {
            _id = id;
            _name = name;
            _email = email;
            _password = password;
            _locked = locked;
            _userType = userType;
            _location = location;
        }

        /**
         * Make a new User; defaults the location to be the null location (empty) for non
         *      Location Employees
         * @param id            the User's id
         * @param name          the User's name
         * @param email         the User's email
         * @param password      the User's password
         * @param locked        the User's account status
         * @param userType      the User's account type
         *
         */
        public User(int id, String name, String email,
                    String password, boolean locked, UserTypes userType) {
            this(id, name, email, password, locked, userType, Model.getInstance().theNullLocation);
        }

        @Override
        public boolean equals(Object c) {
            User o = (User) c;
            return (o.get_Email().equals(_email) && o.get_Password().equals(_password));
        }

        @Override
        public String toString() {
            return _id + ": " +_email;
        }


        /**
         * Constructor used by Parcel to make a new User out of the
         * parceled information
         *
         * @param in  the parcel containing the student information
         */
        private User(Parcel in) {
            _email = in.readString();
            _userType = (UserTypes) in.readSerializable();
            _id = in.readInt();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        /* *************************
           If you add new instance vars to Student, you will need to add them to the write
           Be sure the order you write information matches the order that the constructor above
           reads them.
         */
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(_email);
            dest.writeSerializable(_userType);
            dest.writeInt(_id);
        }

        /**
         * Should not have to edit this method if the constructor and write method are
         * working correctly.
         */
        public static final Parcelable.Creator<User> CREATOR
                = new Parcelable.Creator<User>() {
            @Override
            public User createFromParcel(Parcel in) {
                return new User(in);
            }
            @Override
            public User[] newArray(int size) {
                return new User[size];
            }
        };

        /**
         * should add a new location to the database
         *
         * @param location the location to be added
         * @return whether or not it succeeded
         * @throws NoSuchMethodException not implemented
         */
        public boolean addLocation(Location location) throws NoSuchMethodException{
            throw new NoSuchMethodException();
        }

        /**
         * adds a new user to the database
         *
         * @param user the user to be added
         * @return whether or not it succeeded
         * @throws NoSuchMethodException not implemented
         */
        public boolean addUser(User user) throws NoSuchMethodException {
            throw new NoSuchMethodException();
        }

        /**
         * adds a new item to the inventory of the location of the user
         *
         * @param item the item to be added
         * @return whether or not it succeeded
         * @throws NoSuchMethodException not implemented
         */
        public boolean addItem(Item item) throws NoSuchMethodException {
            throw new NoSuchMethodException();
        }

        /**
         * makes a graph of total item by category, of the users location
         *
         *
         * @return location of graph
         * @throws NoSuchMethodException not implemented yet
         */
        public String makeCatGraph() throws NoSuchMethodException {
            throw new NoSuchMethodException();
        }

        /**
         * makes a graph of inventory value  of the users location
         *
         * @return location of graph
         * @throws NoSuchMethodException not implemented yet
         */
        public String makeValueByMonthGraph() throws NoSuchMethodException {
            throw new NoSuchMethodException();
        }

        /**
         * makes a graph of the income per month of the user's location
         *
         * @return the location of the graph
         * @throws NoSuchMethodException not implemented
         */
        public String makeIncomePerMonthGraph() throws NoSuchMethodException {
            throw new NoSuchMethodException();
        }

        /**
         * makes a graph of the donations per month of the users location
         * @return the location of the graph
         * @throws NoSuchMethodException not implemented
         */
        public String makeDonationsPerMonthGraph() throws NoSuchMethodException {
            throw new NoSuchMethodException();
        }

        /**
         * search for a location that has a specific item
         *
         * @param item the item we are searching for
         * @return an ArrayList containing the locations of the items
         * @throws NoSuchMethodException not implemented yet
         */
        public ArrayList<Location> searchForLocationByItem(String item)
                throws NoSuchMethodException {
            throw new NoSuchMethodException();
        }

        /**
         * searches for a location based on items that are within a desired category
         *
         * @param itemCategory the category to be searched for
         * @return an ArrayList of locations
         * @throws NoSuchMethodException not implemented yet
         */
        public ArrayList<Location> searchForLocationByCategory(String itemCategory)
                throws NoSuchMethodException {
            throw new NoSuchMethodException();
        }

        /**
         * searches for nearby locations
         *
         * @param location the location to look near (default current location?)
         * @return ArrayList of nearby locations
         * @throws NoSuchMethodException not implemented
         */
        public ArrayList<Location> searchForNearbyLocations(String location)
                throws NoSuchMethodException {
            throw new NoSuchMethodException();
        }




    }



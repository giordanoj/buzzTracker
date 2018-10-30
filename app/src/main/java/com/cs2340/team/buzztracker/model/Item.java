package com.cs2340.team.buzztracker.model;

public class Item {

    /**
     * this is the unique id number for the item
     */
    private int _id;

    /**
     * this is the category for a particular item
     */
    private String _category;

    /**
     * This is the donation time of a particular item
     */
    private String _donationTime;

    /**
     * This is the value of an item
     */
    private String _value;

    /**
     * This is a link to the picture for an item
     */
    private String _picture;

    /**
     * This is the comments on an item, at the time of creation
     */
    private String _comment;

    /**
     * This is a short description of the item
     */
    private String _shortDescription;

    /**
     * This is a full description of the item
     */
    private String _fullDescription;

    /**
     * This is the origin of an item
     * it is the id of the location it was brought in at
     */
    private int _origin;

    /**
     * this is the sale time of an item
     * put the definition for not sold here
     */
    private String _saleTime;

    /**
     * this is who entered an item into the database
     * it is the employees id
     */
    private int _enteredBy;

    /**
     * this is who sold the item
     * it is the employees id
     */
    private int _soldBy;

    /**
     * this is the id number of current location of the item
     */
    private int _currentLocation;

    /*
        getters and setters
     */

    public int get_id() {
        return _id;
    }

    public String get_category() {
        return _category;
    }

    public void set_category(String _category) {
        this._category = _category;
    }

    public String get_donationTime() {
        return _donationTime;
    }

    public void set_donationTime(String _donationTime) {
        this._donationTime = _donationTime;
    }

    public String get_value() {
        return _value;
    }

    public void set_value(String _value) {
        this._value = _value;
    }

    public String get_picture() {
        return _picture;
    }

    public void set_picture(String _picture) {
        this._picture = _picture;
    }

    public String get_comment() {
        return _comment;
    }

    public void set_comment(String _comment) {
        this._comment = _comment;
    }

    public String get_shortDescription() {
        return _shortDescription;
    }

    public void set_shortDescription(String _shortDescription) {
        this._shortDescription = _shortDescription;
    }

    public String get_fullDescription() {
        return _fullDescription;
    }

    public void set_fullDescription(String _fullDescription) {
        this._fullDescription = _fullDescription;
    }

    public int get_origin() {
        return _origin;
    }

    public void set_origin(int _origin) {
        this._origin = _origin;
    }

    public String get_saleTime() {
        return _saleTime;
    }

    public void set_saleTime(String _saleTime) {
        this._saleTime = _saleTime;
    }

    public int get_enteredBy() {
        return _enteredBy;
    }

    public void set_enteredBy(int _enteredBy) {
        this._enteredBy = _enteredBy;
    }

    public int get_soldBy() {
        return _soldBy;
    }

    public void set_soldBy(int _soldBy) {
        this._soldBy = _soldBy;
    }

    public int get_currentLocation() {
        return _currentLocation;
    }

    public void set_currentLocation(int _currentLocation) {
        this._soldBy = _currentLocation;
    }


    /**
     * Make an item
     *
     * @param _id                   The item's unique id number
     * @param _category             The category an item falls into
     * @param _donationTime         The donation time of an item
     * @param _value                The Value assigned to an item
     * @param _picture              The link to a picture of an item
     * @param _comment              Any comments attached to an item
     * @param _shortDescription     A short description of an item
     * @param _fullDescription      A full description of an item
     * @param _origin               The origin of an item (by id of location)
     * @param _saleTime             The sale time of an item
     * @param _enteredBy            Who entered an item into a system (by id of employee)
     * @param _soldBy               Who sold an item (by id of employee)
     * @param _currentLocation      The current location of the item (by id of location)
     */
    public Item(int _id, String _category, String _donationTime, String _value, String _picture,
                String _comment, String _shortDescription, String _fullDescription, int _origin,
                String _saleTime, int _enteredBy, int _soldBy, int _currentLocation) {
        this._id = _id;
        this._category = _category;
        this._donationTime = _donationTime;
        this._value = _value;
        this._picture = _picture;
        this._comment = _comment;
        this._shortDescription = _shortDescription;
        this._fullDescription = _fullDescription;
        this._origin = _origin;
        this._saleTime = _saleTime;
        this._enteredBy = _enteredBy;
        this._soldBy = _soldBy;
        this._currentLocation = _currentLocation;
    }
}

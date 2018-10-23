package com.cs2340.team.buzztracker.model;

public class Item {

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

    /*
        getters and setters
     */

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

}

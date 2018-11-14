package com.cs2340.team.buzztracker.model;

/**
 * class for containing item information
 * */
public class Item {

    /**
     * this is the unique id number for the item
     */
    private final int _id;

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

    /**
     *
     * @return id of item
     */
    public int get_id() {
        return _id;
    }

    /**
     *
     * @return category of item
     */
    public String get_category() {
        return _category;
    }

    /**
     *
     * @param _category category item should be in
     */
    public void set_category(String _category) {
        this._category = _category;
    }

    /**
     *
     * @return the donation time of item
     */
    public String get_donationTime() {
        return _donationTime;
    }

    /**
     *
     * @param _donationTime the item should have been at
     */
    public void set_donationTime(String _donationTime) {
        this._donationTime = _donationTime;
    }

    /**
     *
     * @return value of item
     */
    public String get_value() {
        return _value;
    }

    /**
     *
     * @param _value the item should be
     */
    public void set_value(String _value) {
        this._value = _value;
    }

    /**
     *
     * @return location of picture of item if available
     */
    public String get_picture() {
        return _picture;
    }

    /**
     *
     * @param _picture new location of picture of item
     */
    public void set_picture(String _picture) {
        this._picture = _picture;
    }

    /**
     *
     * @return comment on an item
     */
    public String get_comment() {
        return _comment;
    }


    /**
     *
     * @param _comment change comment on item to this
     */
    public void set_comment(String _comment) {
        this._comment = _comment;
    }

    /**
     *
     * @return short description of item
     */
    public String get_shortDescription() {
        return _shortDescription;
    }

    /**
     *
     * @param _shortDescription what to change to
     */
    public void set_shortDescription(String _shortDescription) {
        this._shortDescription = _shortDescription;
    }

    /**
     *
     * @return full description of item
     */
    public String get_fullDescription() {
        return _fullDescription;
    }

    /**
     *
     * @param _fullDescription change to this
     */
    public void set_fullDescription(String _fullDescription) {
        this._fullDescription = _fullDescription;
    }

    /**
     *
     * @return the id of the original location the item was donated at
     */
    public int get_origin() {
        return _origin;
    }

    /**
     *
     * @param _origin change the origin to new location id
     */
    public void set_origin(int _origin) {
        this._origin = _origin;
    }

    /**
     *
     * @return sale time of object
     */
    public String get_saleTime() {
        return _saleTime;
    }

    /**
     *
     * @param _saleTime set to this
     */
    public void set_saleTime(String _saleTime) {
        this._saleTime = _saleTime;
    }

    /**
     *
     * @return user id of who entered it into system
     */
    public int get_enteredBy() {
        return _enteredBy;
    }

    /**
     *
     * @param _enteredBy change who entered it into system
     */
    public void set_enteredBy(int _enteredBy) {
        this._enteredBy = _enteredBy;
    }

    /**
     *
     * @return id of user who sold item
     */
    public int get_soldBy() {
        return _soldBy;
    }

    /**
     *
     * @param _soldBy change who sold item
     */
    public void set_soldBy(int _soldBy) {
        this._soldBy = _soldBy;
    }

    /**
     *
     * @return id of current location of item
     */
    public int get_currentLocation() {
        return _currentLocation;
    }


    /**
     *
     * @param _currentLocation change the location of item
     */
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

    @Override
    public boolean equals(Object c) {
        Item i = (Item) c;
        return ( (i.get_id() == _id) && (i.get_category().equals(_category))
                && (i.get_donationTime().equals(_donationTime) && i.get_value().equals(_value))
                && (i.get_picture().equals(_picture) && i.get_comment().equals(_comment))
                && (i.get_shortDescription().equals(_shortDescription))
                && (i.get_fullDescription().equals(_fullDescription))
                && (i.get_origin() == _origin && i.get_saleTime().equals(_saleTime))
                && (i.get_enteredBy() == _enteredBy && i.get_soldBy() == _soldBy)
                && (i.get_currentLocation() == _currentLocation)
        );
    }
}

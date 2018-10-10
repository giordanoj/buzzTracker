package com.cs2340.team.buzztracker.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class User implements Parcelable {

    /** this user id number */
    private int _id;

    /** this users Username */
    private String _username;

    /** this user's Name */
    private String _name;

    /** this user type */
    private UserTypes _userType;

    /** this user's password */
    private String _password;


    /* **********************
     * Getters and setters
     */
    public String get_Username() { return _username; }
    public void set_Username(String username) { _username = username; }

    public String get_Name() { return _name; }
    public void set_Name(String name) { _name = name; }

    public String get_Password() { return _password; }
    public void set_Password(String password) { _password = password ; }


    //no setter for this.  id is a read only field
    public int getId() { return _id; }

    public UserTypes getUserType() {return _userType; }
    public void setUserType(UserTypes type) { _userType = type; }


    /**
     * Make a new User
     * @param name          the User's Name
     * @param username      the User's Username
     * @param password     the User's Password
     * @param userType   the User's user Type
     * @param id          the User's id number
     */
    public User(int id, String name, String username, String password, UserTypes userType) {
        _id = id;
        _name = name;
        _username = username;
        _userType = userType;
        _password = password;
    }

    /**
     * Make a new User, default to type User
     * @param id            the User's id number
     * @param name          the User's Name
     * @param username      the User's Username
     * @param password     the User's Password
     *
     */
    public User(int id, String name, String username, String password) {
        this(id, name, username, password, UserTypes.User);
    }

    @Override
    public boolean equals(Object c) {
        User o = (User) c;
        return (o.get_Name().equals(_name) && o.get_Username().equals(_username) && o.get_Password().equals(_password));
    }





    @Override
    public String toString() {
        return _username.toString() + _id + "";
    }


    /**
     * Constructor used by Parcel to make a new User out of the
     * parceled information
     *
     * @param in  the parcel containing the student information
     */
    private User(Parcel in) {
        _id = in.readInt();
        _name = in.readString();
        _username = in.readString();
        _password = in.readString();
        _userType = (UserTypes) in.readSerializable();
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
        dest.writeInt(_id);
        dest.writeString(_name);
        dest.writeString(_username);
        dest.writeString(_password);
        dest.writeSerializable(_userType);
    }

    /**
     * Should not have to edit this method if the constructor and write method are
     * working correctly.
     */
    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

}

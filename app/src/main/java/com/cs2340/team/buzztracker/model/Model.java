package com.cs2340.team.buzztracker.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** this class stores all the users */

public class Model {

    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    /** the map of all registered users. key is username, value is User object */
    private Map<String, User> _users;

    /** Null Object pattern, returned when no user is found */
    private final User theNullUser = new User("No Such User","None" );

    /**
     * make a new model
     */
    private Model() {
        _users = new HashMap<>();
    }

    /**
     * Adds the requested user.  If user is already in the class, return false
     * This is an O(n) search
     *
     * This assumes all usernames are unique
     *
     * @param user   the user to add
     * @return true if success, false if user ?
     */
    public boolean addUser(User user) {
        String username = user.get_Username();
        if (! _users.containsKey(username)) {
            _users.put(username,user);
            return true;
        }
        return false;
    }

    /**
     * test validity of username and password combo, user for login
     * @param username String username
     * @param password String password
     * @return true if valid user/pass, false otherwise
     */
    public boolean userLogin(String username, String password) {
        User user = _users.get(username);
        if (user == null) {
            return false;
        } else {
            return user.get_Password().equals(password);
        }
    }

    /**
     * test if username is already taken
     * @param username
     * @return
     */
    public boolean usernameAvailable(String username) {
        return !_users.containsKey(username);
    }
}

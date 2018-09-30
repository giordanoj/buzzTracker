package com.cs2340.team.buzztracker.model;


import java.util.ArrayList;
import java.util.List;

/** this class stores all the users */

public class Model {

    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }


    /** the list of all registered students for this course */
    private List<User> _users;

    /** Null Object pattern, returned when no course is found */
    private final User theNullUser = new User("No Such User","None" );

    /**
     * make a new model
     */
    private Model() {
        _users = new ArrayList<>();
    }



    /**
     * Return a course that has the matching id
     * This uses a linear O(n) search
     *
     * @param id the id number of the course
     * @return the course with this id or theNullCourse if no such id exists.
     */
    public User getUserById(int id) {
        for (User c : _users ) {
            if (c.getId() == id) {
                return c;
            }
        }
        return theNullUser;
    }

    /**
     * add a student to the current course
     *
     * @param user the user to add
     * @return true if student added, false if not added
     */
    /**
     * Adds the requested user.  If user is already in the class, return false
     * This is an O(n) search
     *
     * This assumes all usernames are unique
     *
     * @param user   the user to add to the course
     * @return true if success, false if user already in the class
     */
    public boolean addUser(User user) {

        //go through each student looking for duplicate name   O(n)
        for (User s : _users) {
            if (s.get_Username().equals(user.get_Username())) {
                //oops found duplicate name, don't add and return failure signal
                return false;
            }
        }
        //never found the name so safe to add it.
        _users.add(user);

        //return the success signal
        return true;
    }

    /**
     * return list of users
     */
    public List<User> getList(){
        return _users;
    }

}

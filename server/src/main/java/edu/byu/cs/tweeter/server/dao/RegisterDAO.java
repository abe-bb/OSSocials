package edu.byu.cs.tweeter.server.dao;

import edu.byu.cs.tweeter.model.domain.User;

public class RegisterDAO extends AuthenticationDAO {
    public User register(String firstName, String lastName, String alias, String password) {
        return new User(firstName, lastName, alias, "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
    }

}

package edu.byu.cs.tweeter.server.dao;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class LoginDAO extends AuthenticationDAO {

    public User authenticateUser(String alias, String hashedPassword) {
        return new User("Bloopy", "Noopy", alias, "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
    }


}

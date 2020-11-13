package edu.byu.cs.tweeter.server.dao;

import edu.byu.cs.tweeter.model.domain.User;

public class RegisterDAO extends AuthenticationDAO {
    public User register(String firstName, String lastName, String alias, String imageURL) {
        return new User(firstName, lastName, alias, imageURL);
    }

}

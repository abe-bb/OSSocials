package edu.byu.cs.tweeter.server.dao;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class AuthenticationDAO {
    public AuthToken getToken(User user) {
        return new AuthToken(user.getAlias() + " AuthToken");
    }
}

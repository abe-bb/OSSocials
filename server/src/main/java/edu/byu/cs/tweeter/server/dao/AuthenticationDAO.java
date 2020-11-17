package edu.byu.cs.tweeter.server.dao;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class AuthenticationDAO {
    public AuthToken createToken(User user) {
        return new AuthToken(user.getAlias() + " AuthToken");
    }
}

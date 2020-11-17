package edu.byu.cs.tweeter.server.dao;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;

public class LogoutDAO {
    public boolean logout(User loggedInUser, AuthToken token) {
        return true;
    }
}

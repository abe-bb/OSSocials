package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class LogoutRequest {
    private final User loggedInUser;
    private final AuthToken token;

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public AuthToken getToken() {
        return token;
    }

    public LogoutRequest(User loggedInUser, AuthToken token) {
        this.loggedInUser = loggedInUser;
        this.token = token;
    }
}

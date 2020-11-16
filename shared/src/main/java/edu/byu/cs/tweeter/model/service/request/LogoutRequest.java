package edu.byu.cs.tweeter.model.service.request;

import java.util.Objects;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class LogoutRequest {
    private User loggedInUser;
    private AuthToken authToken;

    public LogoutRequest() {}

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public LogoutRequest(User loggedInUser, AuthToken authToken) {
        this.loggedInUser = loggedInUser;
        this.authToken = authToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogoutRequest request = (LogoutRequest) o;
        return loggedInUser.equals(request.loggedInUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loggedInUser);
    }
}

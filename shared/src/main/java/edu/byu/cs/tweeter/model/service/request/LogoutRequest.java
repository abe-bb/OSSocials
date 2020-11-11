package edu.byu.cs.tweeter.model.service.request;

import java.util.Objects;

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

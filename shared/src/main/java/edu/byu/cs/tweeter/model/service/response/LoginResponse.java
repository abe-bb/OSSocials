package edu.byu.cs.tweeter.model.service.response;

import java.io.Serializable;
import java.util.Objects;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

/**
 * A response for a {@link edu.byu.cs.tweeter.model.service.request.LoginRequest}.
 */
public class LoginResponse extends Response implements Serializable {

    private User user;
    private AuthToken authToken;

    public void setUser(User user) {
        this.user = user;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    /**
     * Creates a response indicating that the corresponding request was unsuccessful.
     *
     * @param message a message describing why the request was unsuccessful.
     */
    public LoginResponse(String message) {
        super(false, message);
    }

    /**
     * Creates a response indicating that the corresponding request was successful.
     *
     * @param user the now logged in user.
     * @param authToken the auth token representing this user's session with the server.
     */
    public LoginResponse(User user, AuthToken authToken) {
        super(true, null);
        this.user = user;
        this.authToken = authToken;
    }

    /**
     * Returns the logged in user.
     *
     * @return the user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Returns the auth token.
     *
     * @return the auth token.
     */
    public AuthToken getAuthToken() {
        return authToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LoginResponse response = (LoginResponse) o;
        return Objects.equals(user, response.user) &&
                Objects.equals(authToken, response.authToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, authToken);
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "user=" + user +
                ", authToken=" + authToken +
                '}';
    }
}

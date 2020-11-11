package edu.byu.cs.tweeter.model.service.response;

import java.util.Objects;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class RegisterResponse extends Response {
    private User user;
    private AuthToken token;

    public RegisterResponse(User user, AuthToken token) {
        super(true);
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public AuthToken getAuthToken() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RegisterResponse that = (RegisterResponse) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, token);
    }

    public RegisterResponse(String message) {
        super(false,  message);
        this.user = null;
        this.token = null;
    }
}

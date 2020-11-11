package edu.byu.cs.tweeter.model.service.request;

import java.util.Objects;

/**
 * Contains all the information needed to make a login request.
 */
public class LoginRequest {

    private String alias;
    private String password;

    private LoginRequest() {};

    /**
     * Creates an instance.
     *
     * @param alias the username of the user to be logged in.
     * @param password the password of the user to be logged in.
     */
    public LoginRequest(String alias, String password) {
        this.alias = alias;
        this.password = password;
    }

    /**
     * Returns the username of the user to be logged in by this request.
     *
     * @return the username.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Returns the password of the user to be logged in by this request.
     *
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginRequest that = (LoginRequest) o;
        return alias.equals(that.alias) &&
                password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alias, password);
    }
}

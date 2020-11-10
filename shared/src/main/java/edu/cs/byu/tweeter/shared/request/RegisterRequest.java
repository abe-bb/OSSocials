package edu.cs.byu.tweeter.shared.request;

import android.graphics.Bitmap;

import java.util.Objects;

public class RegisterRequest {
    private final String firstName;
    private final String lastName;
    private final String alias;
    private final String password;
    private final Bitmap photo;

    public RegisterRequest(String firstName, String lastName, String alias, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.alias = alias;
        this.password = password;
        this.photo = null;
    }

    public RegisterRequest(String firstName, String lastName, String alias, String password, Bitmap photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.alias = alias;
        this.password = password;
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterRequest that = (RegisterRequest) o;
        return firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                alias.equals(that.alias) &&
                password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, alias, password);
    }
}

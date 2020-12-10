package edu.byu.cs.tweeter.model.service.request;

//import android.graphics.Bitmap;

import java.util.Objects;

public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String alias;
    private String password;
    private byte[] photo;

    public RegisterRequest() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }





    public RegisterRequest(String firstName, String lastName, String alias, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.alias = "@" + alias;
        this.password = password;
        this.photo = null;
    }

    public RegisterRequest(String firstName, String lastName, String alias, String password, byte[] photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.alias = "@" + alias;
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

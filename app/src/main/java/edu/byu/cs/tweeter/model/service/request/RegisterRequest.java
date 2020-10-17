package edu.byu.cs.tweeter.model.service.request;

import android.graphics.Bitmap;

public class RegisterRequest {
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String password;
    private final Bitmap photo;

    public RegisterRequest(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.photo = null;
    }

    public RegisterRequest(String firstName, String lastName, String username, String password, Bitmap photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.photo = photo;
    }
}

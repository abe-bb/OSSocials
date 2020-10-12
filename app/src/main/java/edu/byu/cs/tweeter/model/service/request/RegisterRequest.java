package edu.byu.cs.tweeter.model.service.request;

public class RegisterRequest {
    String firstName;
    String lastName;
    String username;
    String password;
    Object photo;
//    probably more info


    public RegisterRequest(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }
}

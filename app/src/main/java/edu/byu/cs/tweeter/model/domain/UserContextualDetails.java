package edu.byu.cs.tweeter.model.domain;

public class UserContextualDetails {
    User viewee;
    int numFollowers;
    int numFollowing;
    boolean isBeingFollowed;

    User viewer;

    public UserContextualDetails(User viewee, int numFollowers, int numFollowing, boolean isBeingFollowed, User viewer) {
        this.viewee = viewee;
        this.numFollowers = numFollowers;
        this.numFollowing = numFollowing;
        this.isBeingFollowed = isBeingFollowed;
        this.viewer = viewer;
    }
}

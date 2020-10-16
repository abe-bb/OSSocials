package edu.byu.cs.tweeter.model.domain;

public class UserContextualDetails {
    User viewee;
    int numFollowers;
    int numFollowing;
    boolean isFollowing;

    User viewer;

    public UserContextualDetails(User viewee, int numFollowers, int numFollowing, boolean isFollowing, User viewer) {
        this.viewee = viewee;
        this.numFollowers = numFollowers;
        this.numFollowing = numFollowing;
        this.isFollowing = isFollowing;
        this.viewer = viewer;
    }

    public User getViewee() {
        return viewee;
    }

    public int getNumFollowers() {
        return numFollowers;
    }

    public int getNumFollowing() {
        return numFollowing;
    }

    public boolean isFollowing() {
        return isFollowing;
    }

    public User getViewer() {
        return viewer;
    }
}

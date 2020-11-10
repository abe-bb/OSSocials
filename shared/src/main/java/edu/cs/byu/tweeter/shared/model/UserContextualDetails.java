package edu.cs.byu.tweeter.shared.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserContextualDetails that = (UserContextualDetails) o;
        return numFollowers == that.numFollowers &&
                numFollowing == that.numFollowing &&
                isFollowing == that.isFollowing &&
                viewee.equals(that.viewee) &&
                viewer.equals(that.viewer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(viewee, numFollowers, numFollowing, isFollowing, viewer);
    }
}

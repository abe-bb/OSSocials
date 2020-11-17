package edu.byu.cs.tweeter.model.service.request;

import java.util.Objects;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class FollowRequest {
    private User follower;
    private User followee;
    private boolean unfollow;
    private AuthToken authToken;

    public FollowRequest(User follower, User followee, boolean unfollow, AuthToken authToken) {
        this.follower = follower;
        this.followee = followee;
        this.unfollow = unfollow;
        this.authToken = authToken;
    }

    public FollowRequest() {}

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public void setFollowee(User followee) {
        this.followee = followee;
    }

    public void setUnfollow(boolean unfollow) {
        this.unfollow = unfollow;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    public User getFollower() {
        return follower;
    }

    public User getFollowee() {
        return followee;
    }

    public boolean isUnfollow() {
        return unfollow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowRequest request = (FollowRequest) o;
        return unfollow == request.unfollow &&
                follower.equals(request.follower) &&
                followee.equals(request.followee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(follower, followee, unfollow);
    }
}

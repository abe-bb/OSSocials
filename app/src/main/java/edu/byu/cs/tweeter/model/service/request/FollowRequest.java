package edu.byu.cs.tweeter.model.service.request;

import java.util.Objects;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class FollowRequest {
    private final User follower;
    private final User followee;
    private final boolean unfollow;
    private final AuthToken token;

    public FollowRequest(User follower, User followee, boolean unfollow, AuthToken token) {
        this.follower = follower;
        this.followee = followee;
        this.unfollow = unfollow;
        this.token = token;
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

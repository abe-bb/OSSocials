package edu.byu.cs.tweeter.model.service.request;

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
}

package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.User;

public class FollowRequest {
    public User follower;
    public User followee;
    public boolean unfollow;

    public FollowRequest(User follower, User followee, boolean unfollow) {
        this.follower = follower;
        this.followee = followee;
        this.unfollow = unfollow;
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

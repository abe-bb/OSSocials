package edu.byu.cs.tweeter.model.service.response;

public class FollowResponse extends Response {
    boolean unfollow;

    public FollowResponse(boolean success, boolean unfollow) {
        super(true);
        this.unfollow = unfollow;
    }

    public FollowResponse(String message, boolean unfollow) {
        super(false,  message);
        this.unfollow = unfollow;
    }

    public boolean isUnfollow() {
        return unfollow;
    }
}

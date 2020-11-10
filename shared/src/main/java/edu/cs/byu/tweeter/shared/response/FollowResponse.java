package edu.cs.byu.tweeter.shared.response;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowResponse that = (FollowResponse) o;
        return unfollow == that.unfollow;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unfollow);
    }
}

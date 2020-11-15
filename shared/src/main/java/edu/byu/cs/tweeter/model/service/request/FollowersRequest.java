package edu.byu.cs.tweeter.model.service.request;

import java.util.Objects;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

/**
 * All required information to get the next page of Followers for a given followee
 */
public class FollowersRequest {
    private User followee;
    private int limit;
    private User lastFollower;
    private AuthToken token;

    public FollowersRequest() {}

    public void setFollowee(User followee) {
        this.followee = followee;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setLastFollower(User lastFollower) {
        this.lastFollower = lastFollower;
    }

    public AuthToken getToken() {
        return token;
    }

    public void setToken(AuthToken token) {
        this.token = token;
    }

    /**
     * @param followee the {@link User} whose followers are being requested
     * @param limit the maximum number of followers to fetch
     * @param lastFollower the last follower from the last request. Null if none.
     */
    public FollowersRequest(User followee, int limit, User lastFollower, AuthToken token) {
        this.followee = followee;
        this.limit = limit;
        this.lastFollower = lastFollower;
        this.token = token;
    }

    public User getFollowee() {
        return followee;
    }

    public int getLimit() {
        return limit;
    }

    public User getLastFollower() {
        return lastFollower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowersRequest that = (FollowersRequest) o;
        return limit == that.limit &&
                followee.equals(that.followee) &&
                Objects.equals(lastFollower, that.lastFollower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(followee, limit, lastFollower);
    }
}



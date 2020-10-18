package edu.byu.cs.tweeter.model.service.request;

import java.util.Objects;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

/**
 * Contains all the information needed to make a request to have the server return the next page of
 * followees for a specified follower.
 */
public class FollowingRequest {

    private final User follower;
    private final int limit;
    private final User lastFollowee;
    private final AuthToken token;

    /**
     * Creates an instance.
     *
     * @param follower the {@link User} whose followees are to be returned.
     * @param limit the maximum number of followees to return.
     * @param lastFollowee the last followee that was returned in the previous request (null if
     *                     there was no previous request or if no followees were returned in the
     *                     previous request).
     */
    public FollowingRequest(User follower, int limit, User lastFollowee, AuthToken token) {
        this.follower = follower;
        this.limit = limit;
        this.lastFollowee = lastFollowee;
        this.token = token;
    }

    /**
     * Returns the follower whose followees are to be returned by this request.
     *
     * @return the follower.
     */
    public User getFollower() {
        return follower;
    }

    /**
     * Returns the number representing the maximum number of followees to be returned by this request.
     *
     * @return the limit.
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Returns the last followee that was returned in the previous request or null if there was no
     * previous request or if no followees were returned in the previous request.
     *
     * @return the last followee.
     */
    public User getLastFollowee() {
        return lastFollowee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowingRequest that = (FollowingRequest) o;
        return limit == that.limit &&
                follower.equals(that.follower) &&
                Objects.equals(lastFollowee, that.lastFollowee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(follower, limit, lastFollowee);
    }
}

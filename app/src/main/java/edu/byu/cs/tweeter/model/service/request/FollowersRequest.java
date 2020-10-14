package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.User;

/**
 * All required information to get the next page of Followers for a given followee
 */
public class FollowersRequest {
    private final User followee;
    private final int limit;
    private final User lastFollower;


    /**
     * @param followee the {@link User} whose followers are being requested
     * @param limit the maximum number of followers to fetch
     * @param lastFollower the last follower from the last request. Null if none.
     */
    public FollowersRequest(User followee, int limit, User lastFollower) {
        this.followee = followee;
        this.limit = limit;
        this.lastFollower = lastFollower;
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
}



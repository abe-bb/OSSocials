package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;

/**
 * Represents a request for both a Feed and Story, the story being a specialization of a feed where
 * the user only follows themselves. 
 */
public class FeedRequest {
    private User user;
    private Status lastStatus;
    private int limit;
    private boolean story;

    /**
     * Constructs a new FeedRequest
     * @param user the user whose feed is being requested
     * @param lastStatus the last fetched status. Null if none
     * @param limit the maximum number of {@link Status} to fetch
     * @param story Whether or not this is a story or a feed Request
     */
    public FeedRequest(User user, Status lastStatus, int limit, boolean story) {
        this.user = user;
        this.lastStatus = lastStatus;
        this.limit = limit;
        this.story = story;
    }

    public User getUser() {
        return user;
    }

    public Status getLastStatus() {
        return lastStatus;
    }

    public int getLimit() {
        return limit;
    }

    public boolean isStory() {
        return story;
    }
}

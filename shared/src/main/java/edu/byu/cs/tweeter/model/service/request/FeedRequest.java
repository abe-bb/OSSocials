package edu.byu.cs.tweeter.model.service.request;

import java.util.Objects;

import edu.byu.cs.tweeter.model.domain.AuthToken;
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

    public void setToken(AuthToken token) {
        this.token = token;
    }

    private AuthToken token;

    /**
     * Constructs a new FeedRequest
     * @param user the user whose feed is being requested
     * @param lastStatus the last fetched status. Null if none
     * @param limit the maximum number of {@link Status} to fetch
     * @param story Whether or not this is a story or a feed Request
     */
    public FeedRequest(User user, Status lastStatus, int limit, boolean story, AuthToken token) {
        this.user = user;
        this.lastStatus = lastStatus;
        this.limit = limit;
        this.story = story;
        this.token = token;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLastStatus(Status lastStatus) {
        this.lastStatus = lastStatus;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setStory(boolean story) {
        this.story = story;
    }

    public AuthToken getToken() {
        return token;
    }

    public FeedRequest() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedRequest request = (FeedRequest) o;
        return limit == request.limit &&
                story == request.story &&
                Objects.equals(user, request.user) &&
                Objects.equals(lastStatus, request.lastStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, lastStatus, limit, story);
    }
}

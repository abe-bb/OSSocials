package edu.byu.cs.tweeter.model.service.response;

import java.util.List;

import edu.byu.cs.tweeter.model.domain.Status;

public class FeedResponse extends PagedResponse {
    List<Status> stati;

    public FeedResponse(String message) {
        super(false, message, false);
        this.stati = null;
    }

    public FeedResponse(List<Status> stati, boolean hasMorePages) {
        super(true, hasMorePages);
        this.stati = stati;
    }

    public List<Status> getStati() {
        return stati;
    }
}

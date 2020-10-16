package edu.byu.cs.tweeter.model.service.response;

import edu.byu.cs.tweeter.model.domain.Status;

public class TwitResponse extends Response {
    Status postedStatus;

    public TwitResponse(Status postedStatus) {
        super(true);
        this.postedStatus = postedStatus;
    }

    public TwitResponse(String message) {
        super(false, message);
    }
}

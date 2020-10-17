package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;

public class TwitRequest {
    private final Status twit;
    private final AuthToken token;

    public TwitRequest(Status twit, AuthToken token) {
        this.twit = twit;
        this.token = token;
    }

    public Status getTwit() {
        return twit;
    }
}

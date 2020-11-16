package edu.byu.cs.tweeter.model.service.request;

import java.util.Objects;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;

public class TwitRequest {
    private Status twit;
    private AuthToken token;

    public TwitRequest() {}

    public TwitRequest(Status twit, AuthToken token) {
        this.twit = twit;
        this.token = token;
    }

    public void setTwit(Status twit) {
        this.twit = twit;
    }

    public AuthToken getToken() {
        return token;
    }

    public void setToken(AuthToken token) {
        this.token = token;
    }

    public Status getTwit() {
        return twit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwitRequest request = (TwitRequest) o;
        return twit.equals(request.twit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(twit);
    }
}

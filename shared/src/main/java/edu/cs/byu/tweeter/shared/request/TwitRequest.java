package edu.cs.byu.tweeter.shared.request;

import java.util.Objects;

import edu.cs.byu.tweeter.shared.model.AuthToken;
import edu.cs.byu.tweeter.shared.model.Status;

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

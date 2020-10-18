package edu.byu.cs.tweeter.model.service.response;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedResponse that = (FeedResponse) o;
        return stati.equals(that.stati);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stati);
    }
}

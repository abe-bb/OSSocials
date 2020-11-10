package edu.cs.byu.tweeter.shared.response;

import java.util.Objects;

import edu.cs.byu.tweeter.shared.model.Status;

public class TwitResponse extends Response {
    Status postedStatus;

    public TwitResponse(Status postedStatus) {
        super(true);
        this.postedStatus = postedStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TwitResponse that = (TwitResponse) o;
        return Objects.equals(postedStatus, that.postedStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), postedStatus);
    }

    public TwitResponse(String message) {
        super(false, message);
    }

}

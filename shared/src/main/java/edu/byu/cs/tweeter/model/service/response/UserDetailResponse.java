package edu.byu.cs.tweeter.model.service.response;

import java.util.Objects;

import edu.byu.cs.tweeter.model.domain.UserContextualDetails;

public class UserDetailResponse extends Response {
    private UserContextualDetails details;

    public void setDetails(UserContextualDetails details) {
        this.details = details;
    }

    public UserDetailResponse(UserContextualDetails details) {
        super(true);
        this.details = details;
    }

    public UserDetailResponse(String message) {
        super(false, message);
        this.details = null;
    }

    public UserContextualDetails getDetails() {
        return details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserDetailResponse that = (UserDetailResponse) o;
        return details.equals(that.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), details);
    }
}

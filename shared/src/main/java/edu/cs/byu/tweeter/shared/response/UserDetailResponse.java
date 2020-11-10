package edu.cs.byu.tweeter.shared.response;

import java.util.Objects;

import edu.cs.byu.tweeter.shared.model.UserContextualDetails;

public class UserDetailResponse extends Response {
    UserContextualDetails details;

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

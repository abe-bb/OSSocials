package edu.byu.cs.tweeter.model.service.response;

import edu.byu.cs.tweeter.model.domain.UserContextualDetails;

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
}

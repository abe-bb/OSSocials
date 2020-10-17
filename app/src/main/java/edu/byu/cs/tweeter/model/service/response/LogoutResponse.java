package edu.byu.cs.tweeter.model.service.response;

import edu.byu.cs.tweeter.model.domain.User;

public class LogoutResponse extends Response {
    public LogoutResponse(boolean success) {
        super(success);
    }

    public LogoutResponse(boolean success, String message) {
        super(success, message);
    }
}

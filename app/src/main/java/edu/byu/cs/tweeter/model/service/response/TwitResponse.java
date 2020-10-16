package edu.byu.cs.tweeter.model.service.response;

public class TwitResponse extends Response {

    TwitResponse(boolean success) {
        super(success);
    }

    TwitResponse(String message) {
        super(false, message);
    }
}

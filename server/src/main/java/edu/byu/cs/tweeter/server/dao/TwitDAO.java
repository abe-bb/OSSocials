package edu.byu.cs.tweeter.server.dao;

import edu.byu.cs.tweeter.model.service.request.TwitRequest;
import edu.byu.cs.tweeter.model.service.response.TwitResponse;

public class TwitDAO {
    public TwitResponse sendTwit(TwitRequest request) {
        return new TwitResponse(request.getTwit());
    }
}

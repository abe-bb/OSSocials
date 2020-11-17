package edu.byu.cs.tweeter.server.dao;

import edu.byu.cs.tweeter.model.service.request.TwitRequest;
import edu.byu.cs.tweeter.model.service.response.TwitResponse;

public class TwitDAO {
    public TwitResponse sendTwit(TwitRequest request) {

        TwitResponse response = new TwitResponse(request.getTwit());
        System.out.println(request.toString());
        return response;
    }
}

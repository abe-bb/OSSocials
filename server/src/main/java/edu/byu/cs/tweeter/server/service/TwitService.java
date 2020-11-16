package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.TwitServiceInterface;
import edu.byu.cs.tweeter.model.service.request.TwitRequest;
import edu.byu.cs.tweeter.model.service.response.TwitResponse;
import edu.byu.cs.tweeter.server.dao.TwitDAO;

public class TwitService implements TwitServiceInterface {
    @Override
    public TwitResponse sendTwit(TwitRequest request) {
        return null;
    }

    private TwitDAO getTwitDAO() {
        return new TwitDAO();
    }
}

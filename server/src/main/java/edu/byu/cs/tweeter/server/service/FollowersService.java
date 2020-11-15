package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;
import edu.byu.cs.tweeter.server.dao.FollowersDAO;

public class FollowersService {

    public FollowersResponse getFollowers(FollowersRequest request) {
        FollowersDAO dao = getFollowersDAO();

        return dao.getFollowers(request);
    }

    FollowersDAO getFollowersDAO() {
        return new FollowersDAO();
    }
}

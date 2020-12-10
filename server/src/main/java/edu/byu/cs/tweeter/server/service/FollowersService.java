package edu.byu.cs.tweeter.server.service;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.model.domain.FeedStatusModel;
import edu.byu.cs.tweeter.model.domain.Follow;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.FollowersServiceInterface;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;
import edu.byu.cs.tweeter.server.dao.FollowersDAO;
import edu.byu.cs.tweeter.server.dao.FollowsDAO;

public class FollowersService extends AuthenticatedService implements FollowersServiceInterface {

    public FollowersResponse getFollowers(FollowersRequest request) {
        if (authorized(request.getFollowee(), request.getToken())) {
            FollowsDAO followsDAO = getFollowsDAO();

            List<Follow> follows = followsDAO.getFollowers(request.getFollowee(), request.getLastFollower());

            ArrayList<User> followerPage = new ArrayList<>();

            boolean hasMorePages = false;
            for (Follow follow : follows) {
                if (request.getLimit() <= followerPage.size()) {
                    hasMorePages = true;
                    break;
                }

                followerPage.add(follow.getFollowee());
            }

            return new FollowersResponse(followerPage, hasMorePages);
        }
        else {
            return new FollowersResponse("Unauthorized session! Please logout and log back in again");
        }
//        FollowersDAO dao = getFollowersDAO();
//
//        return dao.getFollowers(request);
    }

    FollowsDAO getFollowsDAO() {
        return new FollowsDAO();
    }

    FollowersDAO getFollowersDAO() {
        return new FollowersDAO();
    }
}

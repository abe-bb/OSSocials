package edu.byu.cs.tweeter.server.service;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.model.domain.Follow;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.FollowingServiceInterface;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.server.dao.FollowingDAO;
import edu.byu.cs.tweeter.server.dao.FollowsDAO;

/**
 * Contains the business logic for getting the users a user is following.
 */
public class FollowingService extends AuthenticatedService implements FollowingServiceInterface {

    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followees returned and to return the next set of
     * followees after any that were returned in a previous request. Uses the {@link FollowingDAO} to
     * get the followees.
     *
     * @param request contains the data required to fulfill the request.
     * @return the followees.
     */
    @Override
    public FollowingResponse getFollowees(FollowingRequest request) {
        if (authorized(request.getFollower(), request.getAuthToken())) {
            FollowsDAO followsDAO = getFollowsDAO();

            List<Follow> follows = followsDAO.getFollowees(request.getFollower(), request.getLastFollowee());

            ArrayList<User> followingPage = new ArrayList<>();

            boolean hasMorePages = false;
            for (Follow follow : follows) {
                if (request.getLimit() <= followingPage.size()) {
                    hasMorePages = true;
                    break;
                }

                followingPage.add(follow.getFollowee());
            }

            return new FollowingResponse(followingPage, hasMorePages);
        }
        else {
            return new FollowingResponse("Unauthorized session! Please logout and log back in again");
        }
//        return getFollowingDAO().getFollowees(request);
    }

    /**
     * Returns an instance of {@link FollowingDAO}. Allows mocking of the FollowingDAO class
     * for testing purposes. All usages of FollowingDAO should get their FollowingDAO
     * instance from this method to allow for mocking of the instance.
     *
     * @return the instance.
     */
    FollowingDAO getFollowingDAO() {
        return new FollowingDAO();
    }


    FollowsDAO getFollowsDAO() {
        return new FollowsDAO();
    }
}

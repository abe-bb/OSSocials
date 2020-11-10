package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.cs.byu.tweeter.shared.request.FollowersRequest;
import edu.cs.byu.tweeter.shared.response.FollowersResponse;

/**
 * business logic for getting followers
 */
public class FollowersService extends Service {

    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followees returned and to return the next set of
     * followees after any that were returned in a previous request. Uses the {@link ServerFacade} to
     * get the followees from the server.
     *
     * @param request contains the data required to fulfill the request.
     * @return the followees.
     */
    public FollowersResponse getFollowers(FollowersRequest request) throws IOException {
        FollowersResponse response = getServerFacade().getFollowers(request);

        if (response.isSuccess()) {
            loadImages(response.getFollowers());
        }

        return response;
    }

}

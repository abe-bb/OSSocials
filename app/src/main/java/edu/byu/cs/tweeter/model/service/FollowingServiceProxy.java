package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;

/**
 * Contains the business logic for getting the users a user is following.
 */
public class FollowingServiceProxy extends ServiceProxy implements FollowingServiceInterface {

    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followees returned and to return the next set of
     * followees after any that were returned in a previous request. Uses the {@link ServerFacade} to
     * get the followees from the server.
     *
     * @param request contains the data required to fulfill the request.
     * @return the followees.
     */
    public FollowingResponse getFollowees(FollowingRequest request) throws IOException, TweeterRemoteException {

        request.setFollower(stripImages(request.getFollower()));
        request.setLastFollowee(stripImages(request.getLastFollowee()));

        FollowingResponse response = getServerFacade().getFollowees(request);

        if(response.isSuccess()) {
            loadImages(response.getFollowees());
        }

        return response;
    }
}

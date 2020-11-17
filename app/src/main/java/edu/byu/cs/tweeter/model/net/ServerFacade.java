package edu.byu.cs.tweeter.model.net;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.byu.cs.tweeter.BuildConfig;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.domain.UserContextualDetails;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.request.FollowRequest;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.request.TwitRequest;
import edu.byu.cs.tweeter.model.service.request.UserDetailRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;
import edu.byu.cs.tweeter.model.service.response.FollowResponse;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;
import edu.byu.cs.tweeter.model.service.response.RegisterResponse;
import edu.byu.cs.tweeter.model.service.response.TwitResponse;
import edu.byu.cs.tweeter.model.service.response.UserDetailResponse;

/**
 * Acts as a Facade to the Tweeter server. All network requests to the server should go through
 * this class.
 */
public class ServerFacade {
    Server server;

    public ServerFacade() {
        server = null;
    }

    Server getServer() {
        if (server == null) {
            server = new Server();
        }
        return server;
    }

    /**
     * Performs a login and if successful, returns the logged in user and an auth token. The current
     * implementation is hard-coded to return a dummy user and doesn't actually make a network
     * request.
     *
     * @param request contains all information needed to perform a login.
     * @return the login response.
     */
    public LoginResponse login(LoginRequest request) throws TweeterRemoteException {
        return getServer().login(request);
//        return new LoginResponse(user1, new AuthToken("blah"));
    }
    /**
     * Registers a new user, and if successful, returns  the logged in user and an auth token.
     * Currently returns a hardcoded dummy user, without making any network requests.
     */
    public RegisterResponse register(RegisterRequest request) throws TweeterRemoteException {
        return getServer().register(request);
//        return new RegisterResponse(user1, new AuthToken("blah"));
    }

    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followees returned and to return the next set of
     * followees after any that were returned in a previous request. The current implementation
     * returns generated data and doesn't actually make a network request.
     *
     * @param request contains information about the user whose followees are to be returned and any
     *                other information required to satisfy the request.
     * @return the following response.
     */
    public FollowingResponse getFollowees(FollowingRequest request) throws TweeterRemoteException {
        return getServer().getFollowing(request);
    }

    public FollowersResponse getFollowers(FollowersRequest request) throws TweeterRemoteException {
        return getServer().getFollowers(request);
    }

    public FeedResponse getFeed(FeedRequest request) throws TweeterRemoteException {
        return getServer().getFeed(request);
    }


    public TwitResponse sendTwit(TwitRequest request) throws TweeterRemoteException {
        return getServer().sendTwit(request);
    }

    public UserDetailResponse getUserDetails(UserDetailRequest request) throws TweeterRemoteException {
        return getServer().getUserDetails(request);
    }

    public FollowResponse follow(FollowRequest request) throws TweeterRemoteException {
        return getServer().follow(request);
    }

    public LogoutResponse logout(LogoutRequest request) throws TweeterRemoteException {
        return getServer().logout(request);
    }
}

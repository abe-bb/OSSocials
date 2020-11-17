package edu.byu.cs.tweeter.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Instant;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.FollowRequest;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.request.TwitRequest;
import edu.byu.cs.tweeter.model.service.request.UserDetailRequest;
import edu.byu.cs.tweeter.model.service.response.FollowResponse;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;
import edu.byu.cs.tweeter.model.service.response.TwitResponse;
import edu.byu.cs.tweeter.model.service.response.UserDetailResponse;

public class IntegrationMainServicesTest {
    private TwitRequest validTwitRequest;

    private UserDetailRequest validDetailRequest;

    private FollowRequest validFollowRequest;

    private LogoutRequest validLogoutRequest;

    private MainServicesProxy mainServicesProxy;

    @BeforeEach
    public void setup() throws TweeterRemoteException {
        User author = new User("test", "user", "testyMcTestFace", null);
        User viewee = new User("test", "user", "theOneBeingViewed", null);
        Status twit = new Status(author, Instant.now(), "this is my twit. It says things");
        Status twit2 = new Status(author, Instant.now(), "another twit");

        validTwitRequest = new TwitRequest(twit, new AuthToken("bleeBlah"));

        validDetailRequest = new UserDetailRequest(viewee, author, new AuthToken("bleeBlah"));

        validFollowRequest = new FollowRequest(author, viewee, false, new AuthToken("bleeBlah"));

        validLogoutRequest = new LogoutRequest(author, new AuthToken("bleeBlah"));

        mainServicesProxy = new MainServicesProxy();
    }

    @Test
    public void testSendTwit_validRequest_validResponse() throws TweeterRemoteException {
        TwitResponse response = mainServicesProxy.sendTwit(validTwitRequest);
        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    public void testGetUserDetails_validRequest_validResponse() throws IOException, TweeterRemoteException {
        UserDetailResponse response = mainServicesProxy.getUserDetails(validDetailRequest);
        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    public void testFollow_validRequest_validResponse() throws TweeterRemoteException {
        FollowResponse response = mainServicesProxy.follow(validFollowRequest);
        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    public void testLogout_validRequest_validResponse() throws TweeterRemoteException {
        LogoutResponse response = mainServicesProxy.logout(validLogoutRequest);
        Assertions.assertTrue(response.isSuccess());
    }
}

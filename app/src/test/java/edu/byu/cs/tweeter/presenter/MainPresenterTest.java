package edu.byu.cs.tweeter.presenter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.Instant;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.domain.UserContextualDetails;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.MainServicesProxy;
import edu.byu.cs.tweeter.model.service.request.FollowRequest;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.request.TwitRequest;
import edu.byu.cs.tweeter.model.service.request.UserDetailRequest;
import edu.byu.cs.tweeter.model.service.response.FollowResponse;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;
import edu.byu.cs.tweeter.model.service.response.TwitResponse;
import edu.byu.cs.tweeter.model.service.response.UserDetailResponse;

import static org.mockito.Mockito.when;

public class MainPresenterTest {
    private TwitRequest validTwitRequest;
    private TwitRequest invalidTwitRequest;
    private TwitResponse validTwitResponse;
    private TwitResponse invalidTwitResponse;

    private UserDetailRequest validDetailRequest;
    private UserDetailRequest invalidDetailRequest;
    private UserDetailResponse validDetailResponse;
    private UserDetailResponse invalidDetailResponse;

    private FollowRequest validFollowRequest;
    private FollowRequest invalidFollowRequest;
    private FollowResponse validFollowResponse;
    private FollowResponse invalidFollowResponse;

    private LogoutRequest validLogoutRequest;
    private LogoutRequest invalidLogoutRequest;
    private LogoutResponse validLogoutResponse;
    private LogoutResponse invalidLogoutResponse;

    private MainPresenter mainPresenterSpy;

    @BeforeEach
    public void setup() throws IOException, TweeterRemoteException {
        User author = new User("test", "user", "testyMcTestFace", null);
        User viewee = new User("test", "user", "theOneBeingViewed", null);
        Status twit = new Status(author, Instant.now(), "this is my twit. It says things");
        Status twit2 = new Status(author, Instant.now(), "another twit");

        UserContextualDetails details = new UserContextualDetails(viewee, 100, 100, true, author);

        validTwitRequest = new TwitRequest(twit, new AuthToken("bleeBlah"));
        invalidTwitRequest = new TwitRequest(twit2, null);
        validTwitResponse = new TwitResponse(twit);
        invalidTwitResponse = new TwitResponse("Unauthorized");

        validDetailRequest = new UserDetailRequest(viewee, author, new AuthToken("bleeBlah"));
        invalidDetailRequest = new UserDetailRequest(author, author, null);
        validDetailResponse = new UserDetailResponse(details);
        invalidDetailResponse = new UserDetailResponse("Unauthorized");

        validFollowRequest = new FollowRequest(author, viewee, false, new AuthToken("bleeBlah"));
        invalidFollowRequest = new FollowRequest(author, author, false, null);
        validFollowResponse = new FollowResponse(true, false);
        invalidFollowResponse = new FollowResponse("Unauthorized", false);

        validLogoutRequest = new LogoutRequest(author, new AuthToken("bleeBlah"));
        invalidLogoutRequest = new LogoutRequest(viewee, null);
        validLogoutResponse = new LogoutResponse(true);
        invalidLogoutResponse = new LogoutResponse("Unauthorized");

        MainServicesProxy mockMainServicesProxy = Mockito.mock(MainServicesProxy.class);
        when(mockMainServicesProxy.sendTwit(invalidTwitRequest)).thenReturn(invalidTwitResponse);
        when(mockMainServicesProxy.sendTwit(validTwitRequest)).thenReturn(validTwitResponse);

        when(mockMainServicesProxy.getUserDetails(validDetailRequest)).thenReturn(validDetailResponse);
        when(mockMainServicesProxy.getUserDetails(invalidDetailRequest)).thenReturn(invalidDetailResponse);
//
        when(mockMainServicesProxy.follow(validFollowRequest)).thenReturn(validFollowResponse);
        when(mockMainServicesProxy.follow(invalidFollowRequest)).thenReturn(invalidFollowResponse);
//
        when(mockMainServicesProxy.logout(validLogoutRequest)).thenReturn(validLogoutResponse);
        when(mockMainServicesProxy.logout(invalidLogoutRequest)).thenReturn(invalidLogoutResponse);

        MainPresenter.View view = new MainPresenter.View() {};
        mainPresenterSpy = Mockito.spy(new MainPresenter(view));
        when(mainPresenterSpy.getMainService()).thenReturn(mockMainServicesProxy);

    }

    @Test
    public void testSendTwit_validRequest_validResponse() throws TweeterRemoteException {
        TwitResponse response = mainPresenterSpy.sendTwit(validTwitRequest);
        Assertions.assertEquals(validTwitResponse, response);
    }

    @Test
    public void testSendTwit_invalidRequest_correctResponse() throws TweeterRemoteException {
        TwitResponse response = mainPresenterSpy.sendTwit(invalidTwitRequest);
        System.out.println(response.equals(invalidTwitResponse));
        Assertions.assertEquals(invalidTwitResponse, response);

    }

    @Test
    public void testGetUserDetails_validRequest_validResponse() throws IOException, TweeterRemoteException {
        UserDetailResponse response = mainPresenterSpy.getUserDetails(validDetailRequest);
        Assertions.assertEquals(validDetailResponse, response);
    }

    @Test
    public void testGetUserDetails_invalidRequest_correctResponse() throws IOException, TweeterRemoteException {
        UserDetailResponse response = mainPresenterSpy.getUserDetails(invalidDetailRequest);
        Assertions.assertEquals(invalidDetailResponse, response);
    }

    @Test
    public void testFollow_validRequest_validResponse() throws TweeterRemoteException {
        FollowResponse response = mainPresenterSpy.follow(validFollowRequest);
        Assertions.assertEquals(validFollowResponse, response);
    }

    @Test
    public void testFollow_invalidRequest_correctResponse() throws TweeterRemoteException {
        FollowResponse response = mainPresenterSpy.follow(invalidFollowRequest);
        Assertions.assertEquals(invalidFollowResponse, response);
    }

    @Test
    public void testLogout_validRequest_validResponse() throws TweeterRemoteException {
        LogoutResponse response = mainPresenterSpy.logout(validLogoutRequest);
        Assertions.assertEquals(validLogoutResponse, response);
    }

    @Test
    public void testLogout_invalidRequest_correctResponse() throws TweeterRemoteException {
        LogoutResponse response = mainPresenterSpy.logout(invalidLogoutRequest);
        Assertions.assertEquals(invalidLogoutResponse, response);
    }
}

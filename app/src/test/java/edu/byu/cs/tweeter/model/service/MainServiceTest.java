package edu.byu.cs.tweeter.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.Instant;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.domain.UserContextualDetails;
import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.request.FollowRequest;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.request.TwitRequest;
import edu.byu.cs.tweeter.model.service.request.UserDetailRequest;
import edu.byu.cs.tweeter.model.service.response.FollowResponse;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;
import edu.byu.cs.tweeter.model.service.response.TwitResponse;
import edu.byu.cs.tweeter.model.service.response.UserDetailResponse;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

public class MainServiceTest {
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

    private MainService mainServiceSpy;

    @BeforeEach
    public void setup() {
        User author = new User("test", "user", "testyMcTestFace", null);
        User viewee = new User("test", "user", "theOneBeingViewed", null);
        Status twit = new Status(author, Instant.now(), "this is my twit. It says things");
        Status twit2 = new Status(author, Instant.now(), "another twit");

        UserContextualDetails details = new UserContextualDetails(viewee, 100, 100, true, author);

        validTwitRequest = new TwitRequest(twit, new AuthToken());
        invalidTwitRequest = new TwitRequest(twit2, null);
        validTwitResponse = new TwitResponse(twit);
        invalidTwitResponse = new TwitResponse("Unauthorized");

        validDetailRequest = new UserDetailRequest(viewee, author, new AuthToken());
        invalidDetailRequest = new UserDetailRequest(author, author, null);
        validDetailResponse = new UserDetailResponse(details);
        invalidDetailResponse = new UserDetailResponse("Unauthorized");

        validFollowRequest = new FollowRequest(author, viewee, false, new AuthToken());
        invalidFollowRequest = new FollowRequest(author, author, false, null);
        validFollowResponse = new FollowResponse(true, false);
        invalidFollowResponse = new FollowResponse("Unauthorized", false);

        validLogoutRequest = new LogoutRequest(author, new AuthToken());
        invalidLogoutRequest = new LogoutRequest(viewee, null);
        validLogoutResponse = new LogoutResponse(true);
        invalidLogoutResponse = new LogoutResponse("Unauthorized");

        ServerFacade mockServerFacade = Mockito.mock(ServerFacade.class);
        when(mockServerFacade.sendTwit(invalidTwitRequest)).thenReturn(invalidTwitResponse);
        when(mockServerFacade.sendTwit(validTwitRequest)).thenReturn(validTwitResponse);

        when(mockServerFacade.getUserDetails(validDetailRequest)).thenReturn(validDetailResponse);
        when(mockServerFacade.getUserDetails(invalidDetailRequest)).thenReturn(invalidDetailResponse);
//
        when(mockServerFacade.follow(validFollowRequest)).thenReturn(validFollowResponse);
        when(mockServerFacade.follow(invalidFollowRequest)).thenReturn(invalidFollowResponse);
//
        when(mockServerFacade.logout(validLogoutRequest)).thenReturn(validLogoutResponse);
        when(mockServerFacade.logout(invalidLogoutRequest)).thenReturn(invalidLogoutResponse);

        mainServiceSpy = Mockito.spy(new MainService());
        when(mainServiceSpy.getServerFacade()).thenReturn(mockServerFacade);

    }

    @Test
    public void testSendTwit_validRequest_validResponse() {
        TwitResponse response = mainServiceSpy.sendTwit(validTwitRequest);
        Assertions.assertEquals(validTwitResponse, response);
    }

    @Test
    public void testSendTwit_invalidRequest_correctResponse() {
        TwitResponse response = mainServiceSpy.sendTwit(invalidTwitRequest);
        System.out.println(response.equals(invalidTwitResponse));
        Assertions.assertEquals(invalidTwitResponse, response);

    }

    @Test
    public void testGetUserDetails_validRequest_validResponse() throws IOException {
        UserDetailResponse response = mainServiceSpy.getUserDetails(validDetailRequest);
        Assertions.assertEquals(validDetailResponse, response);
    }

    @Test
    public void testGetUserDetails_invalidRequest_correctResponse() throws IOException {
        UserDetailResponse response = mainServiceSpy.getUserDetails(invalidDetailRequest);
        Assertions.assertEquals(invalidDetailResponse, response);
    }

    @Test
    public void testFollow_validRequest_validResponse() {
        FollowResponse response = mainServiceSpy.follow(validFollowRequest);
        Assertions.assertEquals(validFollowResponse, response);
    }

    @Test
    public void testFollow_invalidRequest_correctResponse() {
        FollowResponse response = mainServiceSpy.follow(invalidFollowRequest);
        Assertions.assertEquals(invalidFollowResponse, response);
    }

    @Test
    public void testLogout_validRequest_validResponse() {
        LogoutResponse response = mainServiceSpy.logout(validLogoutRequest);
        Assertions.assertEquals(validLogoutResponse, response);
    }

    @Test
    public void testLogout_invalidRequest_correctResponse() {
        LogoutResponse response = mainServiceSpy.logout(invalidLogoutRequest);
        Assertions.assertEquals(invalidLogoutResponse, response);
    }
}

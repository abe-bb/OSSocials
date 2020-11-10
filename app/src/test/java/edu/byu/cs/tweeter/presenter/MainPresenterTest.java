package edu.byu.cs.tweeter.presenter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.Instant;

import edu.cs.byu.tweeter.shared.model.AuthToken;
import edu.cs.byu.tweeter.shared.model.Status;
import edu.cs.byu.tweeter.shared.model.User;
import edu.cs.byu.tweeter.shared.model.UserContextualDetails;
import edu.byu.cs.tweeter.model.service.MainService;
import edu.cs.byu.tweeter.shared.request.FollowRequest;
import edu.cs.byu.tweeter.shared.request.LogoutRequest;
import edu.cs.byu.tweeter.shared.request.TwitRequest;
import edu.cs.byu.tweeter.shared.request.UserDetailRequest;
import edu.cs.byu.tweeter.shared.response.FollowResponse;
import edu.cs.byu.tweeter.shared.response.LogoutResponse;
import edu.cs.byu.tweeter.shared.response.TwitResponse;
import edu.cs.byu.tweeter.shared.response.UserDetailResponse;

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
    public void setup() throws IOException {
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

        MainService mockMainService = Mockito.mock(MainService.class);
        when(mockMainService.sendTwit(invalidTwitRequest)).thenReturn(invalidTwitResponse);
        when(mockMainService.sendTwit(validTwitRequest)).thenReturn(validTwitResponse);

        when(mockMainService.getUserDetails(validDetailRequest)).thenReturn(validDetailResponse);
        when(mockMainService.getUserDetails(invalidDetailRequest)).thenReturn(invalidDetailResponse);
//
        when(mockMainService.follow(validFollowRequest)).thenReturn(validFollowResponse);
        when(mockMainService.follow(invalidFollowRequest)).thenReturn(invalidFollowResponse);
//
        when(mockMainService.logout(validLogoutRequest)).thenReturn(validLogoutResponse);
        when(mockMainService.logout(invalidLogoutRequest)).thenReturn(invalidLogoutResponse);

        MainPresenter.View view = new MainPresenter.View() {};
        mainPresenterSpy = Mockito.spy(new MainPresenter(view));
        when(mainPresenterSpy.getMainService()).thenReturn(mockMainService);

    }

    @Test
    public void testSendTwit_validRequest_validResponse() {
        TwitResponse response = mainPresenterSpy.sendTwit(validTwitRequest);
        Assertions.assertEquals(validTwitResponse, response);
    }

    @Test
    public void testSendTwit_invalidRequest_correctResponse() {
        TwitResponse response = mainPresenterSpy.sendTwit(invalidTwitRequest);
        System.out.println(response.equals(invalidTwitResponse));
        Assertions.assertEquals(invalidTwitResponse, response);

    }

    @Test
    public void testGetUserDetails_validRequest_validResponse() throws IOException {
        UserDetailResponse response = mainPresenterSpy.getUserDetails(validDetailRequest);
        Assertions.assertEquals(validDetailResponse, response);
    }

    @Test
    public void testGetUserDetails_invalidRequest_correctResponse() throws IOException {
        UserDetailResponse response = mainPresenterSpy.getUserDetails(invalidDetailRequest);
        Assertions.assertEquals(invalidDetailResponse, response);
    }

    @Test
    public void testFollow_validRequest_validResponse() {
        FollowResponse response = mainPresenterSpy.follow(validFollowRequest);
        Assertions.assertEquals(validFollowResponse, response);
    }

    @Test
    public void testFollow_invalidRequest_correctResponse() {
        FollowResponse response = mainPresenterSpy.follow(invalidFollowRequest);
        Assertions.assertEquals(invalidFollowResponse, response);
    }

    @Test
    public void testLogout_validRequest_validResponse() {
        LogoutResponse response = mainPresenterSpy.logout(validLogoutRequest);
        Assertions.assertEquals(validLogoutResponse, response);
    }

    @Test
    public void testLogout_invalidRequest_correctResponse() {
        LogoutResponse response = mainPresenterSpy.logout(invalidLogoutRequest);
        Assertions.assertEquals(invalidLogoutResponse, response);
    }
}

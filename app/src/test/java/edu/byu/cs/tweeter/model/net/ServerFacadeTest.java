package edu.byu.cs.tweeter.model.net;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.lang.reflect.Array;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.request.TwitRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;
import edu.byu.cs.tweeter.model.service.response.FollowResponse;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.model.service.response.TwitResponse;
import edu.byu.cs.tweeter.view.asyncTasks.GetFollowersTask;

class ServerFacadeTest {

    private final User user1 = new User("Daffy", "Duck", "");
    private final User user2 = new User("Fred", "Flintstone", "");
    private final User user3 = new User("Barney", "Rubble", "");
    private final User user4 = new User("Wilma", "Rubble", "");
    private final User user5 = new User("Clint", "Eastwood", "");
    private final User user6 = new User("Mother", "Teresa", "");
    private final User user7 = new User("Harriett", "Hansen", "");
    private final User user8 = new User("Zoe", "Zabriski", "");

    private final Status status1 = new Status(user1, Instant.now(), "some twit");
    private final Status status2 = new Status(user1, Instant.now(), "some other twit");
    private final Status status3 = new Status(user1, Instant.now(), "some other other twit");

    private final AuthToken token = new AuthToken("this is an authToken");

    private final FeedRequest validFeedRequest = new FeedRequest(user1, null, 10, false, token);
    private final FeedRequest validStoryRequest = new FeedRequest(user2, null, 10, false, token);
    private final FollowersRequest validFollowersRequest = new FollowersRequest(user1, 10, null, token);
    private final FollowingRequest validFollowingRequest = new FollowingRequest(user1, 10, null, token);
    private final TwitRequest validTwitRequest = new TwitRequest(status1, token);

    private final FeedResponse validFeedResponse = new FeedResponse(Arrays.asList(status1, status2, status3), false);
    private final FeedResponse validStoryResponse = new FeedResponse(Arrays.asList(status3, status2, status1), false);
    private final FollowersResponse validFollowersResponse = new FollowersResponse(Arrays.asList(user2, user3, user4, user5), true);
    private final FollowingResponse validFollowingResponse = new FollowingResponse(Arrays.asList(user6, user7, user8), true);
    private final TwitResponse validTwitResponse = new TwitResponse(status1);

    private ServerFacade serverFacadeSpy;



    @BeforeEach
    void setup() throws TweeterRemoteException {
        Server mockServer = Mockito.mock(Server.class);
        serverFacadeSpy = Mockito.spy(new ServerFacade());

        Mockito.when(mockServer.getFeed(validFeedRequest)).thenReturn(validFeedResponse);
        Mockito.when(mockServer.getFeed(validStoryRequest)).thenReturn(validStoryResponse);
        Mockito.when(mockServer.getFollowers(validFollowersRequest)).thenReturn(validFollowersResponse);
        Mockito.when(mockServer.getFollowing(validFollowingRequest)).thenReturn(validFollowingResponse);
        Mockito.when(mockServer.sendTwit(validTwitRequest)).thenReturn(validTwitResponse);

        Mockito.when(serverFacadeSpy.getServer()).thenReturn(mockServer);
    }

    @Test
    void testGetFollowing() throws TweeterRemoteException {
        FollowingResponse response = serverFacadeSpy.getFollowees(validFollowingRequest);
        Assertions.assertEquals(validFollowingResponse, response);
    }

    @Test
    void testGetFollowers() throws TweeterRemoteException {
        FollowersResponse response = serverFacadeSpy.getFollowers(validFollowersRequest);
        Assertions.assertEquals(validFollowersResponse, response);
    }

    @Test
    void testGetFeed() throws TweeterRemoteException {
        FeedResponse response = serverFacadeSpy.getFeed(validFeedRequest);
        Assertions.assertEquals(validFeedResponse, response);
    }

    @Test
    void testGetStory() throws TweeterRemoteException {
        FeedResponse response = serverFacadeSpy.getFeed(validStoryRequest);
        Assertions.assertEquals(validStoryResponse, response);
    }


    @Test
    void testSendTwit() throws TweeterRemoteException {
        TwitResponse response = serverFacadeSpy.sendTwit(validTwitRequest);
        Assertions.assertEquals(validTwitResponse, response);
    }
}

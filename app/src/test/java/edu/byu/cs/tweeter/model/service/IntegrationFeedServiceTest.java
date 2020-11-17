package edu.byu.cs.tweeter.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;

public class IntegrationFeedServiceTest {
    private FeedRequest validRequest;
    private FeedRequest validStoryFeedRequest;
    private FeedRequest invalidRequest;

    private FeedResponse successResponse;
    private FeedResponse validStoryFeedResponse;
    private FeedResponse failureResponse;

    private FeedServiceProxy feedServiceProxy;

    @BeforeEach
    public void setup() throws TweeterRemoteException {
        User currentUser = new User("test", "user", "testUser", null);
//
//        ArrayList<Status> statuses = new ArrayList<>();
//        statuses.add(new  Status(feedAuthor1, Instant.now(), "hello"));
//        statuses.add(new  Status(feedAuthor1, Instant.now(), "world!"));
//        statuses.add(new  Status(feedAuthor2, Instant.now(), "G'day m8!"));

        validRequest = new FeedRequest(currentUser, null, 2, false, new AuthToken("bleeBlah"));
        validStoryFeedRequest = new FeedRequest(currentUser, null, 2, true, new AuthToken("bleeBlah"));
        invalidRequest = new FeedRequest(null, null, 2, false, new AuthToken("bleeBlah"));

//        ServerFacade mockServerFacade = Mockito.mock(ServerFacade.class);
//        Mockito.when(mockServerFacade.getFeed(validRequest)).thenReturn(successResponse);
//        Mockito.when(mockServerFacade.getFeed(validStoryFeedRequest)).thenReturn(validStoryFeedResponse);
//        Mockito.when(mockServerFacade.getFeed(invalidRequest)).thenReturn(failureResponse);
//
//        feedServiceProxySpy = Mockito.spy(new FeedServiceProxy());
//        Mockito.when(feedServiceProxySpy.getServerFacade()).thenReturn(mockServerFacade);

        feedServiceProxy = new FeedServiceProxy();
    }

    @Test
    public void testGetFeed_validRequest_correctResponse() throws IOException, TweeterRemoteException {
        FeedResponse response = feedServiceProxy.getFeedPage(validRequest);
        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    public void testGetFeedStory_validRequest_correctResponse() throws IOException, TweeterRemoteException {
        FeedResponse response = feedServiceProxy.getFeedPage(validStoryFeedRequest);
        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    public void testGetFeed_invalidRequest_correctResponse() throws IOException, TweeterRemoteException {
        FeedResponse response = feedServiceProxy.getFeedPage(invalidRequest);
        Assertions.assertFalse(response.isSuccess());

    }
}

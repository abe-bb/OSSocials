package edu.byu.cs.tweeter.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;

import edu.cs.byu.tweeter.shared.model.AuthToken;
import edu.cs.byu.tweeter.shared.model.Status;
import edu.cs.byu.tweeter.shared.model.User;
import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.cs.byu.tweeter.shared.request.FeedRequest;
import edu.cs.byu.tweeter.shared.response.FeedResponse;

public class FeedServiceTest {
    private FeedRequest validRequest;
    private FeedRequest validStoryFeedRequest;
    private FeedRequest invalidRequest;

    private FeedResponse successResponse;
    private FeedResponse validStoryFeedResponse;
    private FeedResponse failureResponse;

    private FeedService feedServiceSpy;

    @BeforeEach
    public void setup() {
        User currentUser = new User("test", "user", "testUser", null);
        User feedAuthor1 = new User("feedAuthor1", "user", "author1", null);
        User feedAuthor2 = new User("feedAuthor1", "user", "author2", null);

        ArrayList<Status> statuses = new ArrayList<>();
        statuses.add(new  Status(feedAuthor1, Instant.now(), "hello"));
        statuses.add(new  Status(feedAuthor1, Instant.now(), "world!"));
        statuses.add(new  Status(feedAuthor2, Instant.now(), "G'day m8!"));

        validRequest = new FeedRequest(currentUser, null, 2, false, new AuthToken());
        validStoryFeedRequest = new FeedRequest(currentUser, null, 2, true, new AuthToken());
        invalidRequest = new FeedRequest(null, null, 2, false, new AuthToken());

        successResponse = new FeedResponse(statuses, true);
        validStoryFeedResponse = new FeedResponse(statuses, true);
        failureResponse = new FeedResponse("An error has occurred.");

        ServerFacade mockServerFacade = Mockito.mock(ServerFacade.class);
        Mockito.when(mockServerFacade.getFeedPage(validRequest)).thenReturn(successResponse);
        Mockito.when(mockServerFacade.getFeedPage(validStoryFeedRequest)).thenReturn(validStoryFeedResponse);
        Mockito.when(mockServerFacade.getFeedPage(invalidRequest)).thenReturn(failureResponse);

        feedServiceSpy = Mockito.spy(new FeedService());
        Mockito.when(feedServiceSpy.getServerFacade()).thenReturn(mockServerFacade);
    }

    @Test
    public void testGetFeed_validRequest_correctResponse() throws IOException {
        FeedResponse response = feedServiceSpy.getFeedPage(validRequest);
        Assertions.assertEquals(successResponse, response);
    }

    @Test
    public void testGetFeedStory_validRequest_correctResponse() throws IOException {
        FeedResponse response = feedServiceSpy.getFeedPage(validStoryFeedRequest);
        Assertions.assertEquals(validStoryFeedResponse, response);
    }

    @Test
    public void testGetFeed_invalidRequest_correctResponse() throws IOException {
        FeedResponse response = feedServiceSpy.getFeedPage(invalidRequest);
        Assertions.assertEquals(failureResponse, response);

    }

}

package edu.byu.cs.tweeter.presenter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.FeedService;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;

public class FeedPresenterTest {
    private FeedRequest validRequest;
    private FeedRequest validStoryFeedRequest;
    private FeedRequest invalidRequest;

    private FeedResponse successResponse;
    private FeedResponse validStoryFeedResponse;
    private FeedResponse failureResponse;

    private FeedPresenter FeedPresenterSpy;

    @BeforeEach
    public void setup() throws IOException {
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

        FeedService mockFeedService = Mockito.mock(FeedService.class);
        Mockito.when(mockFeedService.getFeedPage(validRequest)).thenReturn(successResponse);
        Mockito.when(mockFeedService.getFeedPage(validStoryFeedRequest)).thenReturn(validStoryFeedResponse);
        Mockito.when(mockFeedService.getFeedPage(invalidRequest)).thenReturn(failureResponse);

        FeedPresenterSpy = Mockito.spy(new FeedPresenter());
        Mockito.when(FeedPresenterSpy.getFeedService()).thenReturn(mockFeedService);
    }

    @Test
    public void testGetFeed_validRequest_correctResponse() throws IOException {
        FeedResponse response = FeedPresenterSpy.getFeedPage(validRequest);
        Assertions.assertEquals(successResponse, response);
    }

    @Test
    public void testGetFeedStory_validRequest_correctResponse() throws IOException {
        FeedResponse response = FeedPresenterSpy.getFeedPage(validStoryFeedRequest);
        Assertions.assertEquals(validStoryFeedResponse, response);
    }

    @Test
    public void testGetFeed_invalidRequest_correctResponse() throws IOException {
        FeedResponse response = FeedPresenterSpy.getFeedPage(invalidRequest);
        Assertions.assertEquals(failureResponse, response);

    }

}

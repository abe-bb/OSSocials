package edu.byu.cs.tweeter.server.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.Arrays;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;
import edu.byu.cs.tweeter.server.dao.FeedDAO;
import edu.byu.cs.tweeter.server.dao.StoryDAO;

public class FeedServiceTest {
    private static final String MALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png";
    private static final String FEMALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png";
    private final Instant timeStamp = Instant.parse("2020-10-16T00:13:42.879Z");
    private final User user1 = new User("Allen", "Anderson", MALE_IMAGE_URL);
    private final User user2 = new User("Amy", "Ames", FEMALE_IMAGE_URL);
    private final User user3 = new User("Bob", "Bobson", MALE_IMAGE_URL);
    //    feed responses
    private final Status status1 = new Status(user2, timeStamp, "Good Morning!");
    private final Status status2 = new Status(user2, timeStamp, "Why, sweet child. Why?");
    private final Status status3 = new Status(user3, timeStamp, "Hello Tweeter!");

    private final AuthToken token = new AuthToken("alias token");

    private final FeedRequest validFeedReq = new FeedRequest(user1, null, 10, false, token);
    private final FeedRequest validStoryReq = new FeedRequest(user1, null, 10, true, token);
    private final FeedRequest invalidFeedReq = new FeedRequest(null, null, 10, false, token);
    private final FeedRequest invalidStoryReq = new FeedRequest(null, null, 10, true, token);

    private final FeedResponse validFeedRes = new FeedResponse(Arrays.asList(status1, status2, status3), false);
    private final FeedResponse validStoryRes = new FeedResponse(Arrays.asList(status3, status2, status1), false);

    private FeedService feedServiceSpy;

    @BeforeEach
    void setup() {
        feedServiceSpy = Mockito.spy(FeedService.class);

        FeedDAO feedDAO = Mockito.mock(FeedDAO.class);
        StoryDAO storyDAO = Mockito.mock(StoryDAO.class);

        Mockito.when(feedDAO.getFeed(validFeedReq)).thenReturn(validFeedRes);
        Mockito.when(storyDAO.getStory(validStoryReq)).thenReturn(validStoryRes);

        Mockito.when(feedServiceSpy.getFeedDAO()).thenReturn(feedDAO);
        Mockito.when(feedServiceSpy.getStoryDAO()).thenReturn(storyDAO);
    }

    @Test
    void testValidFeedRequest() {
        FeedResponse response = feedServiceSpy.getFeed(validFeedReq);
        Assertions.assertEquals(validFeedRes, response);
    }

    @Test
    void testValidStoryRequest() {
        FeedResponse response = feedServiceSpy.getFeed(validStoryReq);
        Assertions.assertEquals(validStoryRes, response);
    }

    @Test
    void testInvalidFeedRequest() {
        FeedResponse response = feedServiceSpy.getFeed(invalidFeedReq);
        Assertions.assertFalse(response.isSuccess());
    }

    @Test
    void testInvalidStoryRequest() {
        FeedResponse response = feedServiceSpy.getFeed(invalidStoryReq);
        Assertions.assertFalse(response.isSuccess());
    }
}

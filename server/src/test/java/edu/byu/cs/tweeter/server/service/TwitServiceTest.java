package edu.byu.cs.tweeter.server.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.TwitRequest;
import edu.byu.cs.tweeter.model.service.response.TwitResponse;
import edu.byu.cs.tweeter.server.dao.TwitDAO;

public class TwitServiceTest {
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

    private final TwitRequest request = new TwitRequest(status1, token);
    private final TwitResponse response = new TwitResponse(status1);

    private TwitService twitServiceSpy;

    @BeforeEach
    void setup() {
        twitServiceSpy = Mockito.spy(TwitService.class);

        TwitDAO mockDAO = Mockito.mock(TwitDAO.class);

        Mockito.when(mockDAO.sendTwit(request)).thenReturn(response);
    }

    @Test
    void testSendTwit() {
        TwitResponse response = twitServiceSpy.sendTwit(request);
        Assertions.assertEquals(this.response, response);
    }
}

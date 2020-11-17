package edu.byu.cs.tweeter.server.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowRequest;
import edu.byu.cs.tweeter.model.service.response.FollowResponse;
import edu.byu.cs.tweeter.server.dao.FollowDAO;

public class FollowServiceTest {
    private static final String MALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png";
    private static final String FEMALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png";

    private final User user1 = new User("Allen", "Anderson", MALE_IMAGE_URL);
    private final User user2 = new User("Amy", "Ames", FEMALE_IMAGE_URL);
    private final User user3 = new User("Bob", "Bobson", MALE_IMAGE_URL);

    private final AuthToken token = new AuthToken("alias token");

    private final FollowRequest followRequest = new FollowRequest(user1, user2, false, token);
    private final FollowRequest unfollowRequest = new FollowRequest(user1, user2, true, token);

    private final FollowResponse followResponse = new FollowResponse(true, false);
    private final FollowResponse unfollowResponse = new FollowResponse(true, true);

    private FollowService followServiceSpy;

    @BeforeEach
    void setup() {
        followServiceSpy = Mockito.spy(FollowService.class);

        FollowDAO mockDAO = Mockito.mock(FollowDAO.class);
        Mockito.when(mockDAO.follow(followRequest.getFollowee(), followRequest.getFollower())).thenReturn(followResponse.isSuccess());
        Mockito.when(mockDAO.unfollow(unfollowRequest.getFollowee(), unfollowRequest.getFollower())).thenReturn(unfollowResponse.isSuccess());

        Mockito.when(followServiceSpy.getFollowDAO()).thenReturn(mockDAO);
    }

    @Test
    void testFollow() {
        FollowResponse response = followServiceSpy.follow(followRequest);
        Assertions.assertEquals(followResponse, response);
    }

    @Test
    void testUnfollow() {
        FollowResponse response = followServiceSpy.follow(unfollowRequest);
        Assertions.assertEquals(unfollowResponse, response);
    }
}

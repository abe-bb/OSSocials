package edu.byu.cs.tweeter.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;

public class FollowersServiceProxyTest {

    private FollowersRequest validRequest;
    private FollowersRequest invalidRequest;

    private FollowersResponse successResponse;
    private FollowersResponse failureResponse;

    private FollowersServiceProxy followingServiceSpy;

    /**
     * Create a FollowingService spy that uses a mock ServerFacade to return known responses to
     * requests.
     */
    @BeforeEach
    public void setup() throws TweeterRemoteException {
        User currentUser = new User("FirstName", "LastName", null);

        User resultUser1 = new User("FirstName1", "LastName1",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        User resultUser2 = new User("FirstName2", "LastName2",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png");
        User resultUser3 = new User("FirstName3", "LastName3",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png");

        // Setup request objects to use in the tests
        validRequest = new FollowersRequest(currentUser, 3, null, new AuthToken("bleeBlah"));
        invalidRequest = new FollowersRequest(null, 0, null, new AuthToken("bleeBlah"));

        // Setup a mock ServerFacade that will return known responses
        successResponse = new FollowersResponse(Arrays.asList(resultUser1, resultUser2, resultUser3), false);
        ServerFacade mockServerFacade = Mockito.mock(ServerFacade.class);
        Mockito.when(mockServerFacade.getFollowers(validRequest)).thenReturn(successResponse);

        failureResponse = new FollowersResponse("An exception occurred");
        Mockito.when(mockServerFacade.getFollowers(invalidRequest)).thenReturn(failureResponse);

        // Create a FollowingService instance and wrap it with a spy that will use the mock service
        followingServiceSpy = Mockito.spy(new FollowersServiceProxy());
        Mockito.when(followingServiceSpy.getServerFacade()).thenReturn(mockServerFacade);
    }

    /**
     * Verify that for successful requests the {@link FollowingServiceInterface#getFollowees(FollowingRequest)}
     * method returns the same result as the {@link ServerFacade}.
     * .
     *
     * @throws IOException if an IO error occurs.
     */
    @Test
    public void testGetFollowees_validRequest_correctResponse() throws IOException, TweeterRemoteException {
        FollowersResponse response = followingServiceSpy.getFollowers(validRequest);
        Assertions.assertEquals(successResponse, response);
    }

    /**
     * Verify that the {@link FollowingServiceInterface#getFollowees(FollowingRequest)} method loads the
     * profile image of each user included in the result.
     *
     * @throws IOException if an IO error occurs.
     */
    @Test
    public void testGetFollowees_validRequest_loadsProfileImages() throws IOException, TweeterRemoteException {
        FollowersResponse response = followingServiceSpy.getFollowers(validRequest);

        for(User user : response.getFollowers()) {
            Assertions.assertNotNull(user.getImageBytes());
        }
    }

    /**
     * Verify that for failed requests the {@link FollowingServiceInterface#getFollowees(FollowingRequest)}
     * method returns the same result as the {@link ServerFacade}.
     *
     * @throws IOException if an IO error occurs.
     */
    @Test
    public void testGetFollowees_invalidRequest_returnsNoFollowees() throws IOException, TweeterRemoteException {
        FollowersResponse response = followingServiceSpy.getFollowers(invalidRequest);
        Assertions.assertEquals(failureResponse, response);
    }

}

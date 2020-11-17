package edu.byu.cs.tweeter.server.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;
import edu.byu.cs.tweeter.server.dao.FollowersDAO;

public class FollowersServiceTest {

    private FollowersRequest request;
    private FollowersResponse expectedResponse;
    private FollowersDAO mockFollowersDAO;
    private FollowersService FollowersServiceImplSpy;

    @BeforeEach
    public void setup() {
        User currentUser = new User("FirstName", "LastName", null);

        User resultUser1 = new User("FirstName1", "LastName1",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        User resultUser2 = new User("FirstName2", "LastName2",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png");
        User resultUser3 = new User("FirstName3", "LastName3",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png");

        // Setup a request object to use in the tests
        request = new FollowersRequest(currentUser, 3, null, null);

        // Setup a mock FollowersDAO that will return known responses
        expectedResponse = new FollowersResponse(Arrays.asList(resultUser1, resultUser2, resultUser3), false);
        mockFollowersDAO = Mockito.mock(FollowersDAO.class);
        Mockito.when(mockFollowersDAO.getFollowers(request)).thenReturn(expectedResponse);

        FollowersServiceImplSpy = Mockito.spy(FollowersService.class);
        Mockito.when(FollowersServiceImplSpy.getFollowersDAO()).thenReturn(mockFollowersDAO);
    }

    /**
     * Verify that the {@link FollowersService#getFollowers(FollowersRequest)}
     * method returns the same result as the {@link FollowersDAO} class.
     */
    @Test
    public void testGetFollowees_validRequest_correctResponse() throws IOException, TweeterRemoteException {
        FollowersResponse response = FollowersServiceImplSpy.getFollowers(request);
        Assertions.assertEquals(expectedResponse, response);
    }
}

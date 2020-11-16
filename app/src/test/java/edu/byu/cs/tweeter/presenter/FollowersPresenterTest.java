package edu.byu.cs.tweeter.presenter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.FollowersServiceProxy;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;

public class FollowersPresenterTest {

    private FollowersRequest validRequest;
    private FollowersRequest invalidRequest;

    private FollowersResponse successResponse;
    private FollowersResponse failureResponse;

    private FollowersPresenter FollowersPresenterSpy;

    /**
     * Create a FollowingService spy that uses a mock FollowersService to return known responses to
     * requests.
     */
    @BeforeEach
    public void setup() throws IOException, TweeterRemoteException {
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

        // Setup a mock FollowersService that will return known responses
        successResponse = new FollowersResponse(Arrays.asList(resultUser1, resultUser2, resultUser3), false);
        FollowersServiceProxy mockFollowersServiceProxy = Mockito.mock(FollowersServiceProxy.class);
        Mockito.when(mockFollowersServiceProxy.getFollowers(validRequest)).thenReturn(successResponse);

        failureResponse = new FollowersResponse("An exception occurred");
        Mockito.when(mockFollowersServiceProxy.getFollowers(invalidRequest)).thenReturn(failureResponse);

        FollowersPresenter.View view = new FollowersPresenter.View() {};
        FollowersPresenterSpy = Mockito.spy(new FollowersPresenter(view));
        Mockito.when(FollowersPresenterSpy.getFollowersService()).thenReturn(mockFollowersServiceProxy);
    }

    @Test
    public void testGetFollowees_validRequest_correctResponse() throws IOException, TweeterRemoteException {
        FollowersResponse response = FollowersPresenterSpy.getFollowers(validRequest);
        Assertions.assertEquals(successResponse, response);
    }

    @Test
    public void testGetFollowees_invalidRequest_returnsNoFollowees() throws IOException, TweeterRemoteException {
        FollowersResponse response = FollowersPresenterSpy.getFollowers(invalidRequest);
        Assertions.assertEquals(failureResponse, response);
    }

}

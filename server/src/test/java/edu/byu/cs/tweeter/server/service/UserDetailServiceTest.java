package edu.byu.cs.tweeter.server.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.domain.UserContextualDetails;
import edu.byu.cs.tweeter.model.service.request.UserDetailRequest;
import edu.byu.cs.tweeter.model.service.response.UserDetailResponse;
import edu.byu.cs.tweeter.server.dao.UserDetailDAO;

public class UserDetailServiceTest {
    private static final String MALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png";
    private static final String FEMALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png";

    private final User user1 = new User("Allen", "Anderson", MALE_IMAGE_URL);
    private final User user2 = new User("Amy", "Ames", FEMALE_IMAGE_URL);
    private final User user3 = new User("Bob", "Bobson", MALE_IMAGE_URL);

    private final AuthToken token = new AuthToken("alias token");

    private final UserDetailRequest request = new UserDetailRequest(user1, user2, token);
    private final UserContextualDetails details = new UserContextualDetails(user1, (int) (Math.random() * (100000)), (int) (Math.random() * (100000)), true, user2);
    private final UserDetailResponse response = new UserDetailResponse(details);

    private UserDetailService userDetailServiceSpy;

    @BeforeEach
    void setup() {
        userDetailServiceSpy = Mockito.spy(UserDetailService.class);

        UserDetailDAO mockDAO = Mockito.mock(UserDetailDAO.class);

        Mockito.when(mockDAO.getUserDetails(request.getViewee(), request.getViewer())).thenReturn(details);

        Mockito.when(userDetailServiceSpy.getUserDetailDAO()).thenReturn(mockDAO);
    }

    @Test
    void testGetUserDetails() {
        UserDetailResponse response = userDetailServiceSpy.getUserDetails(request);
        Assertions.assertEquals(this.response, response);
    }


}

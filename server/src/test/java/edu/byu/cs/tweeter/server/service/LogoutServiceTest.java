package edu.byu.cs.tweeter.server.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;
import edu.byu.cs.tweeter.server.dao.LogoutDAO;

public class LogoutServiceTest {
    private final User user = new User("alias");
    private final AuthToken token = new AuthToken("alias token");

    private final LogoutRequest validRequest = new LogoutRequest(user, token);
    private final LogoutRequest invalidRequest = new LogoutRequest(null, null);

    private LogoutService logoutServiceSpy;

    @BeforeEach
    void setup() {
        logoutServiceSpy = Mockito.spy(LogoutService.class);

        LogoutDAO mockDAO = Mockito.mock(LogoutDAO.class);

        Mockito.when(mockDAO.logout(validRequest.getLoggedInUser(), validRequest.getAuthToken())).thenReturn(true);
        Mockito.when(mockDAO.logout(invalidRequest.getLoggedInUser(), invalidRequest.getAuthToken())).thenReturn(false);

        Mockito.when(logoutServiceSpy.getLogoutDAO()).thenReturn(mockDAO);
    }

    @Test
    void testValidLogout() {
        LogoutResponse response = logoutServiceSpy.logout(validRequest);
        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    void testInalidLogout() {
        LogoutResponse response = logoutServiceSpy.logout(invalidRequest);
        Assertions.assertFalse(response.isSuccess());
    }
}

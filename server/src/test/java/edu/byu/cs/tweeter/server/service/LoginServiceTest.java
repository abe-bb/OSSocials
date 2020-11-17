package edu.byu.cs.tweeter.server.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.server.dao.LoginDAO;
import sun.rmi.runtime.Log;

public class LoginServiceTest {
    private final User user = new User("alias");
    private final AuthToken token = new AuthToken("alias token");

    private final LoginRequest validRequest = new LoginRequest("alias", "password");
    private final LoginResponse validResponse = new LoginResponse(user, token);

    private final LoginRequest invalidRequest = new LoginRequest("null", null);

    private LoginService loginServiceSpy;

    @BeforeEach
    void setup() {
        loginServiceSpy = Mockito.spy(LoginService.class);
        Mockito.when(loginServiceSpy.hashPassword(validRequest.getPassword())).thenReturn(validRequest.getPassword());

        LoginDAO mockDAO = Mockito.mock(LoginDAO.class);

        Mockito.when(mockDAO.authenticateUser(validRequest.getAlias(), validRequest.getPassword())).thenReturn(user);
        Mockito.when(mockDAO.createToken(user)).thenReturn(token);

        Mockito.when(loginServiceSpy.getLoginDAO()).thenReturn(mockDAO);
    }

    @Test
    void testValidRequest() {
        LoginResponse response = loginServiceSpy.login(validRequest);
        Assertions.assertEquals(validResponse, response);
    }

    @Test
    void testInvalidRequest() {
        LoginResponse response = loginServiceSpy.login(invalidRequest);
        Assertions.assertFalse(response.isSuccess());
    }

}

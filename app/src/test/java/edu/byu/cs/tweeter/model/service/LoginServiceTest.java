package edu.byu.cs.tweeter.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.cs.byu.tweeter.shared.model.AuthToken;
import edu.cs.byu.tweeter.shared.model.User;
import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.cs.byu.tweeter.shared.request.LoginRequest;
import edu.cs.byu.tweeter.shared.response.LoginResponse;

public class LoginServiceTest {

    private LoginRequest validRequest;
    private LoginRequest invalidRequest;

    private LoginResponse validResponse;
    private LoginResponse invalidResponse;

    private LoginService loginServiceSpy;

    @BeforeEach
    public void setup() {

        validRequest = new LoginRequest("testyMcTestFace", "password");
        invalidRequest = new LoginRequest("invalidUsername", "blah");

        validResponse = new LoginResponse(new User("testyMcTestFace"), new AuthToken());
        invalidResponse = new LoginResponse("An error has occurred");

        ServerFacade mockServerFacade = Mockito.mock(ServerFacade.class);
        Mockito.when(mockServerFacade.login(validRequest)).thenReturn(validResponse);
        Mockito.when(mockServerFacade.login(invalidRequest)).thenReturn(invalidResponse);

        loginServiceSpy = Mockito.spy(new LoginService());
        Mockito.when(loginServiceSpy.getServerFacade()).thenReturn(mockServerFacade);
    }

    @Test
    public void testLogin_validRequest_validResponse() throws IOException {
        LoginResponse response = loginServiceSpy.login(validRequest);
        Assertions.assertEquals(validResponse, response);
    }

    @Test
    public void testLogin_invalidRequest_correctResponse() throws IOException {
        LoginResponse response = loginServiceSpy.login(invalidRequest);
        Assertions.assertEquals(invalidResponse, response);
    }
}

package edu.byu.cs.tweeter.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;

public class LoginServiceProxyTest {

    private LoginRequest validRequest;
    private LoginRequest invalidRequest;

    private LoginResponse validResponse;
    private LoginResponse invalidResponse;

    private LoginServiceProxy loginServiceProxySpy;

    @BeforeEach
    public void setup() throws TweeterRemoteException {

        validRequest = new LoginRequest("testyMcTestFace", "password");
        invalidRequest = new LoginRequest("invalidUsername", "blah");

        validResponse = new LoginResponse(new User("testyMcTestFace"), new AuthToken("bleeBlah"));
        invalidResponse = new LoginResponse("An error has occurred");

        ServerFacade mockServerFacade = Mockito.mock(ServerFacade.class);
        Mockito.when(mockServerFacade.login(validRequest)).thenReturn(validResponse);
        Mockito.when(mockServerFacade.login(invalidRequest)).thenReturn(invalidResponse);

        loginServiceProxySpy = Mockito.spy(new LoginServiceProxy());
        Mockito.when(loginServiceProxySpy.getServerFacade()).thenReturn(mockServerFacade);
    }

    @Test
    public void testLogin_validRequest_validResponse() throws IOException, TweeterRemoteException {
        LoginResponse response = loginServiceProxySpy.login(validRequest);
        Assertions.assertEquals(validResponse, response);
    }

    @Test
    public void testLogin_invalidRequest_correctResponse() throws IOException, TweeterRemoteException {
        LoginResponse response = loginServiceProxySpy.login(invalidRequest);
        Assertions.assertEquals(invalidResponse, response);
    }
}

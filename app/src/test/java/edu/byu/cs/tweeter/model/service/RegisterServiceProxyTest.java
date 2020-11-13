package edu.byu.cs.tweeter.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.RegisterResponse;

public class RegisterServiceProxyTest {
    private RegisterRequest validRequest;
    private RegisterRequest invalidRequest;
    private RegisterResponse validResponse;
    private RegisterResponse invalidResponse;

    private RegisterServiceProxy registerServiceProxySpy;

    @BeforeEach
    public void setup() {
        User user1 = new User("test", "user", "user1", null);
        validRequest = new RegisterRequest("test", "user", "user1", "password");
        invalidRequest = new RegisterRequest("blah", "blah", "user1", "blah");

        validResponse = new RegisterResponse(user1, new AuthToken("bleeBlah"));
        invalidResponse = new RegisterResponse("alias already in use.");

        ServerFacade mockServerFacade = Mockito.mock(ServerFacade.class);

        Mockito.when(mockServerFacade.register(validRequest)).thenReturn(validResponse);
        Mockito.when(mockServerFacade.register(invalidRequest)).thenReturn(invalidResponse);

        registerServiceProxySpy = Mockito.spy(new RegisterServiceProxy());
        Mockito.when(registerServiceProxySpy.getServerFacade()).thenReturn(mockServerFacade);
    }

    @Test
    public void testRegister_validRequest_validResponse() throws IOException {
        RegisterResponse response = registerServiceProxySpy.register(validRequest);
        Assertions.assertEquals(validResponse, response);
    }

    @Test
    public void testRegister_invalidRequest_correctResponse() throws IOException {
        RegisterResponse response = registerServiceProxySpy.register(invalidRequest);
        Assertions.assertEquals(invalidResponse, response);
    }
}

package edu.byu.cs.tweeter.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.cs.byu.tweeter.shared.model.AuthToken;
import edu.cs.byu.tweeter.shared.model.User;
import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.cs.byu.tweeter.shared.request.RegisterRequest;
import edu.cs.byu.tweeter.shared.response.RegisterResponse;

public class RegisterServiceTest {
    private RegisterRequest validRequest;
    private RegisterRequest invalidRequest;
    private RegisterResponse validResponse;
    private RegisterResponse invalidResponse;

    private RegisterService registerServiceSpy;

    @BeforeEach
    public void setup() {
        User user1 = new User("test", "user", "user1", null);
        validRequest = new RegisterRequest("test", "user", "user1", "password");
        invalidRequest = new RegisterRequest("blah", "blah", "user1", "blah");

        validResponse = new RegisterResponse(user1, new AuthToken());
        invalidResponse = new RegisterResponse("alias already in use.");

        ServerFacade mockServerFacade = Mockito.mock(ServerFacade.class);

        Mockito.when(mockServerFacade.register(validRequest)).thenReturn(validResponse);
        Mockito.when(mockServerFacade.register(invalidRequest)).thenReturn(invalidResponse);

        registerServiceSpy = Mockito.spy(new RegisterService());
        Mockito.when(registerServiceSpy.getServerFacade()).thenReturn(mockServerFacade);
    }

    @Test
    public void testRegister_validRequest_validResponse() throws IOException {
        RegisterResponse response = registerServiceSpy.register(validRequest);
        Assertions.assertEquals(validResponse, response);
    }

    @Test
    public void testRegister_invalidRequest_correctResponse() throws IOException {
        RegisterResponse response = registerServiceSpy.register(invalidRequest);
        Assertions.assertEquals(invalidResponse, response);
    }
}

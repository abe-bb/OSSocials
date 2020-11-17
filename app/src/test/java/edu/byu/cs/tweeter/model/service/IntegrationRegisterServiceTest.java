package edu.byu.cs.tweeter.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.RegisterResponse;

public class IntegrationRegisterServiceTest {
    private RegisterRequest validRequest;
    private RegisterRequest invalidRequest;
    private RegisterResponse validResponse;
    private RegisterResponse invalidResponse;

    private RegisterServiceProxy registerServiceProxy;

    @BeforeEach
    public void setup() throws TweeterRemoteException {
        User user1 = new User("test", "user", "user1", null);
        validRequest = new RegisterRequest("test", "user", "user1", "password");

        registerServiceProxy = new RegisterServiceProxy();
    }

    @Test
    public void testRegister_validRequest_validResponse() throws IOException, TweeterRemoteException {
        RegisterResponse response = registerServiceProxy.register(validRequest);
        Assertions.assertTrue(response.isSuccess());
    }
}

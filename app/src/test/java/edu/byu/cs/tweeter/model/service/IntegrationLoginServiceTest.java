package edu.byu.cs.tweeter.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;

public class IntegrationLoginServiceTest {

    private LoginRequest validRequest;
    private LoginRequest invalidRequest;

    private LoginResponse validResponse;
    private LoginResponse invalidResponse;

    private LoginServiceProxy loginServiceProxy;

    @BeforeEach
    public void setup() throws TweeterRemoteException {

        validRequest = new LoginRequest("testyMcTestFace", "password");

        loginServiceProxy = new LoginServiceProxy();
    }

    @Test
    public void testLogin_validRequest_validResponse() throws IOException, TweeterRemoteException {
        LoginResponse response = loginServiceProxy.login(validRequest);
        Assertions.assertTrue(response.isSuccess());
    }

}

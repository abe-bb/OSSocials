package edu.byu.cs.tweeter.presenter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.RegisterServiceProxy;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.RegisterResponse;

public class RegisterPresenterTest {
    private RegisterRequest validRequest;
    private RegisterRequest invalidRequest;
    private RegisterResponse validResponse;
    private RegisterResponse invalidResponse;

    private RegisterPresenter registerPresenterSpy;

    @BeforeEach
    public void setup() throws IOException, TweeterRemoteException {
        User user1 = new User("test", "user", "user1", null);
        validRequest = new RegisterRequest("test", "user", "user1", "password");
        invalidRequest = new RegisterRequest("blah", "blah", "user1", "blah");

        validResponse = new RegisterResponse(user1, new AuthToken("bleeBlah"));
        invalidResponse = new RegisterResponse("alias already in use.");

        RegisterServiceProxy mockRegisterServiceProxy = Mockito.mock(RegisterServiceProxy.class);
        RegisterPresenter.View view = new RegisterPresenter.View() {};

        Mockito.when(mockRegisterServiceProxy.register(validRequest)).thenReturn(validResponse);
        Mockito.when(mockRegisterServiceProxy.register(invalidRequest)).thenReturn(invalidResponse);

        registerPresenterSpy = Mockito.spy(new RegisterPresenter(view));
        Mockito.when(registerPresenterSpy.getRegisterService()).thenReturn(mockRegisterServiceProxy);
    }

    @Test
    public void testRegister_validRequest_validResponse() throws IOException, TweeterRemoteException {
        RegisterResponse response = registerPresenterSpy.register(validRequest);
        Assertions.assertEquals(validResponse, response);
    }

    @Test
    public void testRegister_invalidRequest_correctResponse() throws IOException, TweeterRemoteException {
        RegisterResponse response = registerPresenterSpy.register(invalidRequest);
        Assertions.assertEquals(invalidResponse, response);
    }
}

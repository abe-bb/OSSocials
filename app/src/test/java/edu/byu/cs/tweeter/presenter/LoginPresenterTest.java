package edu.byu.cs.tweeter.presenter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.LoginService;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;

public class LoginPresenterTest {

    private LoginRequest validRequest;
    private LoginRequest invalidRequest;

    private LoginResponse validResponse;
    private LoginResponse invalidResponse;

    private LoginPresenter LoginPresenterSpy;

    @BeforeEach
    public void setup() throws IOException, TweeterRemoteException {

        validRequest = new LoginRequest("testyMcTestFace", "password");
        invalidRequest = new LoginRequest("invalidUsername", "blah");

        validResponse = new LoginResponse(new User("testyMcTestFace"), new AuthToken("Bleeblah"));
        invalidResponse = new LoginResponse("An error has occurred");

        LoginService mockLoginService = Mockito.mock(LoginService.class);
        Mockito.when(mockLoginService.login(validRequest)).thenReturn(validResponse);
        Mockito.when(mockLoginService.login(invalidRequest)).thenReturn(invalidResponse);

        LoginPresenter.View view = new LoginPresenter.View() {};
        LoginPresenterSpy = Mockito.spy(new LoginPresenter(view));
        Mockito.when(LoginPresenterSpy.getLoginService()).thenReturn(mockLoginService);
    }

    @Test
    public void testLogin_validRequest_validResponse() throws IOException, TweeterRemoteException {
        LoginResponse response = LoginPresenterSpy.login(validRequest);
        Assertions.assertEquals(validResponse, response);
    }

    @Test
    public void testLogin_invalidRequest_correctResponse() throws IOException, TweeterRemoteException {
        LoginResponse response = LoginPresenterSpy.login(invalidRequest);
        Assertions.assertEquals(invalidResponse, response);
    }
}

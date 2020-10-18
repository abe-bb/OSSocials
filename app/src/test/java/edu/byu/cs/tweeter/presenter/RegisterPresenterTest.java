package edu.byu.cs.tweeter.presenter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.RegisterService;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.RegisterResponse;

public class RegisterPresenterTest {
    private RegisterRequest validRequest;
    private RegisterRequest invalidRequest;
    private RegisterResponse validResponse;
    private RegisterResponse invalidResponse;

    private RegisterPresenter RegisterPresenterSpy;

    @BeforeEach
    public void setup() throws IOException {
        User user1 = new User("test", "user", "user1", null);
        validRequest = new RegisterRequest("test", "user", "user1", "password");
        invalidRequest = new RegisterRequest("blah", "blah", "user1", "blah");

        validResponse = new RegisterResponse(user1, new AuthToken());
        invalidResponse = new RegisterResponse("alias already in use.");

        RegisterService mockRegisterService = Mockito.mock(RegisterService.class);
        RegisterPresenter.View view = new RegisterPresenter.View() {};

        Mockito.when(mockRegisterService.register(validRequest)).thenReturn(validResponse);
        Mockito.when(mockRegisterService.register(invalidRequest)).thenReturn(invalidResponse);

        RegisterPresenterSpy = Mockito.spy(new RegisterPresenter(view));
        Mockito.when(RegisterPresenterSpy.getRegisterService()).thenReturn(mockRegisterService);
    }

    @Test
    public void testRegister_validRequest_validResponse() throws IOException {
        RegisterResponse response = RegisterPresenterSpy.register(validRequest);
        Assertions.assertEquals(validResponse, response);
    }

    @Test
    public void testRegister_invalidRequest_correctResponse() throws IOException {
        RegisterResponse response = RegisterPresenterSpy.register(invalidRequest);
        Assertions.assertEquals(invalidResponse, response);
    }
}

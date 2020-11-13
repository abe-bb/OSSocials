package edu.byu.cs.tweeter.server.lambda;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.server.service.LoginService;

public class LoginHandlerTest {
    private LoginRequest request;
    private LoginResponse response;
    private LoginService mockLoginService;
    private LoginHandler loginHandlerSpy;

    @BeforeEach
    public void setup() {
        request = new LoginRequest("boopy", "password");
        User user = new User("boopy", "noopy", "boopy", "https://bllahblah");
        AuthToken token = new AuthToken("test");
        response = new LoginResponse(user, token);

        mockLoginService = Mockito.mock(LoginService.class);
        Mockito.when(mockLoginService.login(request)).thenReturn(response);

        loginHandlerSpy = Mockito.spy(LoginHandler.class);
    }

    @Test
    public void testCorrectHandlerResponse() {
        LoginResponse response = loginHandlerSpy.handleRequest(request, null);
        System.out.println(response.toString());
        Assertions.assertEquals(this.response, response);
    }
}

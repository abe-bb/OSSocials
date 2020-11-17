package edu.byu.cs.tweeter.server.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.RegisterResponse;
import edu.byu.cs.tweeter.server.dao.RegisterDAO;

public class RegisterServiceTest {
    private final User user = new User("testy", "mcTestFace", "test", null);
    private final AuthToken token = new AuthToken("test token");

    private final RegisterRequest request = new RegisterRequest(user.getFirstName(), user.getLastName(), user.getAlias(), "password");
    private final RegisterResponse response = new RegisterResponse(user, token);

    private RegisterService registerServiceSpy;

    @BeforeEach
    void setup() {
        registerServiceSpy = Mockito.spy(RegisterService.class);

        RegisterDAO mockDAO = Mockito.mock(RegisterDAO.class);

        Mockito.when(mockDAO.register(request.getFirstName(), request.getLastName(), request.getAlias(), request.getPassword())).thenReturn(user);
        Mockito.when(mockDAO.createToken(user)).thenReturn(token);

        Mockito.when(registerServiceSpy.getRegisterDAO()).thenReturn(mockDAO);
    }

    @Test
    public void testRegistration() {
        RegisterResponse response = registerServiceSpy.register(request);
        Assertions.assertEquals(this.response, response);
    }
}

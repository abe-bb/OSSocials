package edu.byu.cs.tweeter.server.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.RegisterServiceInterface;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.RegisterResponse;
import edu.byu.cs.tweeter.server.dao.RegisterDAO;

public class RegisterService extends AuthenticationService implements RegisterServiceInterface {
    @Override
    public RegisterResponse register(RegisterRequest request) {
        RegisterDAO dao = getRegisterDAO();
        User user = dao.register(request.getFirstName(), request.getLastName(), request.getAlias(), hashPassword(request.getPassword()));
        AuthToken token = dao.getToken(user);

        return new RegisterResponse(user, token);
    }

    private RegisterDAO getRegisterDAO() {
        return new RegisterDAO();
    }
}

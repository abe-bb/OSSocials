package edu.byu.cs.tweeter.server.service;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.LoginServiceInterface;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.server.dao.LoginDAO;

public class LoginService extends AuthenticationService implements LoginServiceInterface {

    @Override
    public LoginResponse login(LoginRequest request) {
        LoginDAO dao = getLoginDAO();
        String hashedPassword = hashPassword(request.getPassword());
        User user = dao.authenticateUser(request.getAlias(), hashedPassword);

        return new LoginResponse(user, dao.getToken(user));
    }

    LoginDAO getLoginDAO() {
        return new LoginDAO();
    }
}

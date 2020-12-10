package edu.byu.cs.tweeter.server.service;

import java.security.NoSuchAlgorithmException;

import edu.byu.cs.tweeter.model.domain.AuthModel;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.UserModel;
import edu.byu.cs.tweeter.model.service.LoginServiceInterface;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.server.dao.AuthDAO;
import edu.byu.cs.tweeter.server.dao.LoginDAO;
import edu.byu.cs.tweeter.server.dao.UserDAO;

public class LoginService extends AuthenticationService implements LoginServiceInterface {

    @Override
    public LoginResponse login(LoginRequest request) {
        UserDAO userDAO = getUserDAO();
        AuthDAO authDAO = getAuthDAO();

        UserModel user = userDAO.getUser(request.getAlias());
        if (user == null) {
            return new LoginResponse(String.format("No user with the handle \"%s\" exists", request.getAlias()));
        }

        try {
            String saltedHash = hashPassword(request.getPassword(), user.getSalt());
            AuthModel authModel = authDAO.addToken(user.getUser());
            return new LoginResponse(user.getUser(), new AuthToken(authModel.getToken()));
        }
        catch (NoSuchAlgorithmException e) {
            return new LoginResponse("Server Error. Unable to login right now");
        }
    }

    UserDAO getUserDAO() {
        return new UserDAO();
    }

    AuthDAO getAuthDAO() {
        return new AuthDAO();
    }

    LoginDAO getLoginDAO() {
        return new LoginDAO();
    }
}

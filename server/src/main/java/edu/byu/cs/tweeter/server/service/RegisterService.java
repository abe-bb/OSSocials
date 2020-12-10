package edu.byu.cs.tweeter.server.service;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import edu.byu.cs.tweeter.model.domain.AuthModel;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.RegisterServiceInterface;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.RegisterResponse;
import edu.byu.cs.tweeter.server.dao.AuthDAO;
import edu.byu.cs.tweeter.server.dao.RegisterDAO;
import edu.byu.cs.tweeter.server.dao.UserDAO;

public class RegisterService extends AuthenticationService implements RegisterServiceInterface {
    @Override
    public RegisterResponse register(RegisterRequest request) {
        UserDAO userDAO = getUserDAO();
        AuthDAO authDAO = getAuthDAO();

        User newUser = new User(request.getFirstName(), request.getLastName(), request.getAlias());
        String salt = UUID.randomUUID().toString();

        try {
            String hashedPassword = hashPassword(request.getPassword(), salt);
            userDAO.addUser(newUser, hashedPassword, salt);
            AuthModel authModel = authDAO.addToken(newUser);

            return new RegisterResponse(newUser, new AuthToken(authModel.getToken()));
        }
        catch (NoSuchAlgorithmException e) {
            return new RegisterResponse("Server Error. Unable to register right now");
        }
    }

    UserDAO getUserDAO() {
        return new UserDAO();
    }

    AuthDAO getAuthDAO() {
        return new AuthDAO();
    }

    RegisterDAO getRegisterDAO() {
        return new RegisterDAO();
    }
}

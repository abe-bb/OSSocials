package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.cs.byu.tweeter.shared.request.LoginRequest;
import edu.cs.byu.tweeter.shared.response.LoginResponse;

/**
 * Contains the business logic to support the login operation.
 */
public class LoginService extends Service {

    public LoginResponse login(LoginRequest request) throws IOException {
        ServerFacade serverFacade = getServerFacade();
        LoginResponse loginResponse = serverFacade.login(request);

        if(loginResponse.isSuccess()) {
            loadImage(loginResponse.getUser());
        }

        return loginResponse;
    }
}

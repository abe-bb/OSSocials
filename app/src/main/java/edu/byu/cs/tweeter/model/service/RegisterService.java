package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;

public class RegisterService extends Service {
    public LoginResponse register(RegisterRequest request) throws IOException {
        ServerFacade serverFacade = getServerFacade();


        LoginResponse response = serverFacade.register(request);

        if (response.isSuccess()) {
            LoginService.loadImage(response.getUser());
        }

        return response;
    }

    public ServerFacade getServerFacade() {
        return new ServerFacade();
    }


}

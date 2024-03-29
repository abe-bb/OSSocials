package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.RegisterResponse;

public class RegisterServiceProxy extends ServiceProxy implements RegisterServiceInterface {
    public RegisterResponse register(RegisterRequest request) throws IOException, TweeterRemoteException {
        ServerFacade serverFacade = getServerFacade();


        RegisterResponse response = serverFacade.register(request);

        if (response.isSuccess()) {
            LoginServiceProxy.loadImage(response.getUser());
        }

        return response;
    }
}

package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.RegisterServiceProxy;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.RegisterResponse;

public class RegisterPresenter {

    private final View view;

    public RegisterPresenter(View view) {
        this.view = view;
    }

    /**
     * interface by which this presenter communicates with it's view
     */
    public interface View {

    }

    /**
     * Makes a regester request.
     *
     * @param registerRequest the request to register a new user
     */
    public RegisterResponse register(RegisterRequest registerRequest) throws IOException, TweeterRemoteException {
        RegisterServiceProxy service = getRegisterService();

        return service.register(registerRequest);
    }

    public RegisterServiceProxy getRegisterService() {
        return new RegisterServiceProxy();
    }
}

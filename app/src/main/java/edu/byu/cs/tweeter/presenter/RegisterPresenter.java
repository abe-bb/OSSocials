package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.RegisterService;
import edu.cs.byu.tweeter.shared.request.RegisterRequest;
import edu.cs.byu.tweeter.shared.response.RegisterResponse;

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
    public RegisterResponse register(RegisterRequest registerRequest) throws IOException {
        RegisterService service = getRegisterService();

        return service.register(registerRequest);
    }

    public RegisterService getRegisterService() {
        return new RegisterService();
    }
}

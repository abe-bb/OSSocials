package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.LoginService;
import edu.cs.byu.tweeter.shared.request.LoginRequest;
import edu.cs.byu.tweeter.shared.request.RegisterRequest;
import edu.cs.byu.tweeter.shared.response.LoginResponse;

/**
 * The presenter for the login functionality of the application.
 */
public class LoginPresenter {

    private final View view;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
    }

    /**
     * Creates an instance.
     *
     * @param view the view for which this class is the presenter.
     */
    public LoginPresenter(View view) {
        this.view = view;
    }

    /**
     * Makes a login request.
     *
     * @param loginRequest the request to login a user
     */
    public LoginResponse login(LoginRequest loginRequest) throws IOException {
        LoginService loginService = getLoginService();
        return loginService.login(loginRequest);
    }

    /**
     * allows mocking of the login service
     * @return new LoginService Object
     */
    protected LoginService getLoginService() {
        return new LoginService();
    }
}

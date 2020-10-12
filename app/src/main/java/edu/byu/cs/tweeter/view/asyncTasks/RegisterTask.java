package edu.byu.cs.tweeter.view.asyncTasks;

import android.os.AsyncTask;

import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.presenter.RegisterPresenter;

public class RegisterTask extends AsyncTask<RegisterRequest, Void, LoginResponse> {
    private RegisterPresenter presenter;
    private Observer observer;
    private Exception exception;

    public interface Observer {
        void registerSuccessful(LoginResponse loginResponse);
        void registerUnsuccessful(LoginResponse loginResponse);
        void handleException(Exception ex);
    }

    public RegisterTask(RegisterPresenter presenter, Observer observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected LoginResponse doInBackground(RegisterRequest... registerRequests) {
        LoginResponse response = null;
        try {
            response = presenter.register(registerRequests[0]);
        }
        catch (Exception e) {
            exception = e;
        }
        return response;
    }

    @Override
    protected void onPostExecute(LoginResponse loginResponse) {
        if (exception != null) {
            observer.handleException(exception);
        }
        else if (loginResponse.isSuccess()) {
            observer.registerSuccessful(loginResponse);
        }
        else {
            observer.registerUnsuccessful(loginResponse);
        }
    }
}

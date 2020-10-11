package edu.byu.cs.tweeter.view.asyncTasks;

import android.os.AsyncTask;

import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;

public class RegisterTask extends AsyncTask<RegisterRequest, Void, LoginResponse> {
    private

    public interface Observer {
        void registerSuccessful(LoginResponse loginResponse);
        void registerUnsuccessful(LoginResponse loginResponse);
        void handleException(Exception ex);
    }

    @Override
    protected LoginResponse doInBackground(RegisterRequest... registerRequests) {
        return null;
    }
}

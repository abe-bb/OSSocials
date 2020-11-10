package edu.byu.cs.tweeter.view.asyncTasks;

import android.os.AsyncTask;

import edu.cs.byu.tweeter.shared.request.LogoutRequest;
import edu.cs.byu.tweeter.shared.response.LogoutResponse;
import edu.byu.cs.tweeter.presenter.MainPresenter;

public class LogoutTask extends AsyncTask<LogoutRequest, Void, LogoutResponse> {

    Observer observer;
    MainPresenter presenter;
    Exception exception;

    public LogoutTask(Observer observer, MainPresenter presenter) {
        this.observer = observer;
        this.presenter = presenter;
        exception = null;
    }

    public interface Observer {
        void logoutSuccessful(LogoutResponse response);
        void logoutUnsuccessful(LogoutResponse response);
        void handleException(Exception e);
    }

    @Override
    protected LogoutResponse doInBackground(LogoutRequest... logoutRequests) {
        LogoutResponse response = null;
        try {
            response = presenter.logout(logoutRequests[0]);
        }
        catch (Exception e) {
            exception = e;
        }
        return response;
    }

    @Override
    protected void onPostExecute(LogoutResponse logoutResponse) {
        if (exception != null) {
            observer.handleException(exception);
        }
        else {
            if (logoutResponse.isSuccess()) {
                observer.logoutSuccessful(logoutResponse);
            }
            else {
                observer.logoutUnsuccessful(logoutResponse);
            }

        }
    }
}

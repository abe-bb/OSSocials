package edu.byu.cs.tweeter.view.asyncTasks;

import android.os.AsyncTask;

import edu.byu.cs.tweeter.model.service.request.TwitRequest;
import edu.byu.cs.tweeter.model.service.request.UserDetailRequest;
import edu.byu.cs.tweeter.model.service.response.TwitResponse;
import edu.byu.cs.tweeter.model.service.response.UserDetailResponse;
import edu.byu.cs.tweeter.presenter.MainPresenter;

public class GetUserDetailTask extends AsyncTask<UserDetailRequest, Void, UserDetailResponse> {
    private final Observer observer;
    private final MainPresenter presenter;
    private Exception exception;

    public GetUserDetailTask(Observer observer, MainPresenter presenter) {
        this.observer = observer;
        this.presenter = presenter;
        this.exception = null;
    }

    public interface Observer {
        void userDetailsRetrieved(UserDetailResponse response);
        void handleException(Exception exception);
    }

    @Override
    protected UserDetailResponse doInBackground(UserDetailRequest... requests) {
        UserDetailResponse response = null;
        try {
            response = presenter.getUserDetails(requests[0]);
        }
        catch (Exception e) {
            exception = e;
        }
        return response;
    }

    @Override
    protected void onPostExecute(UserDetailResponse response) {
        if (exception != null) {
            observer.handleException(exception);
        }
        else {
            observer.userDetailsRetrieved(response);
        }
    }
}

package edu.byu.cs.tweeter.view.asyncTasks;

import android.os.AsyncTask;

import edu.cs.byu.tweeter.shared.request.FollowRequest;
import edu.cs.byu.tweeter.shared.response.FollowResponse;
import edu.byu.cs.tweeter.presenter.MainPresenter;

public class FollowTask extends AsyncTask<FollowRequest, Void, FollowResponse> {
    private final Observer observer;
    private final MainPresenter presenter;
    private Exception exception;

    public FollowTask(Observer observer, MainPresenter presenter) {
        this.observer = observer;
        this.presenter = presenter;
        this.exception = null;
    }

    public interface Observer {
        void followComplete(FollowResponse response);
        void handleException(Exception exception);
    }

    @Override
    protected FollowResponse doInBackground(FollowRequest... requests) {
        FollowResponse response = null;
        try {
            response = presenter.follow(requests[0]);
        }
        catch (Exception e) {
            exception = e;
        }
        return response;
    }

    @Override
    protected void onPostExecute(FollowResponse response) {
        if (exception != null) {
            observer.handleException(exception);
        }
        else {
            observer.followComplete(response);
//            if (response.isSuccess())
//                observer.followComplete(response);
//            else
//                observer.followIncomplete(response);
        }
    }
}

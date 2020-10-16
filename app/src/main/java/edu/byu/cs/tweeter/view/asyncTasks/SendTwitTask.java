package edu.byu.cs.tweeter.view.asyncTasks;

import android.os.AsyncTask;

import edu.byu.cs.tweeter.model.service.request.TwitRequest;
import edu.byu.cs.tweeter.model.service.response.TwitResponse;
import edu.byu.cs.tweeter.presenter.MainPresenter;

public class SendTwitTask extends AsyncTask<TwitRequest, Void, TwitResponse> {
    private final Observer observer;
    private final MainPresenter presenter;
    private Exception exception;

    public SendTwitTask(Observer observer, MainPresenter presenter) {
        this.observer = observer;
        this.presenter = presenter;
        this.exception = null;
    }

    public interface Observer {
        void twitSent(TwitResponse response);
//        void twitFailed(TwitResponse response);
        void handleException(Exception exception);
    }

    @Override
    protected TwitResponse doInBackground(TwitRequest... twitRequests) {
        TwitResponse response = null;
        try {
            response = presenter.sendTwit(twitRequests[0]);
        }
        catch (Exception e) {
            exception = e;
        }
        return response;
    }

    @Override
    protected void onPostExecute(TwitResponse twitResponse) {
        if (exception != null) {
            observer.handleException(exception);
        }
        else {
//            if (twitResponse.isSuccess())
//                observer.twitSent(twitResponse);
//            else
//                observer.twitFailed(twitResponse);
            observer.twitSent(twitResponse);
        }
    }
}

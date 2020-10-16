package edu.byu.cs.tweeter.view.asyncTasks;

import android.os.AsyncTask;

import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;
import edu.byu.cs.tweeter.presenter.FeedPresenter;

public class GetFeedTask extends AsyncTask<FeedRequest, Void, FeedResponse> {
    private final FeedPresenter presenter;
    private final Observer observer;
    private Exception exception;

    public interface Observer {
        void feedPageRetrieved(FeedResponse response);
        void handleException(Exception exception);
    }

    public GetFeedTask(FeedPresenter presenter, Observer observer) {
        this.presenter = presenter;
        this.observer = observer;
        this.exception = null;
    }

    @Override
    protected FeedResponse doInBackground(FeedRequest... feedRequests) {
        FeedResponse response = null;

        try {
            response = presenter.getFeedPage(feedRequests[0]);
        }
        catch (Exception exception) {
            this.exception = exception;
        }

        return response;
    }

    @Override
    protected void onPostExecute(FeedResponse feedResponse) {
        if (exception != null) {
            observer.handleException(exception);
        }
        else {
            observer.feedPageRetrieved(feedResponse);
        }
    }
}

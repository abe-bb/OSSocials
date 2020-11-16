package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.FeedServiceProxy;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;

public class FeedPresenter {
    private View view;

    /**
     * interface through which the presenter communicates with it's view
     */
    public interface View {

    }

    public FeedResponse getFeedPage(FeedRequest request) throws IOException, TweeterRemoteException {
        FeedServiceProxy service = getFeedService();
        return service.getFeedPage(request);
    }

    FeedServiceProxy getFeedService() {
        return new FeedServiceProxy();
    }
}

package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.FeedService;
import edu.cs.byu.tweeter.shared.request.FeedRequest;
import edu.cs.byu.tweeter.shared.response.FeedResponse;

public class FeedPresenter {
    private View view;

    /**
     * interface through which the presenter communicates with it's view
     */
    public interface View {

    }

    public FeedResponse getFeedPage(FeedRequest request) throws IOException {
        FeedService service = getFeedService();
        return service.getFeedPage(request);
    }

    FeedService getFeedService() {
        return new FeedService();
    }
}

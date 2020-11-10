package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.cs.byu.tweeter.shared.model.Status;
import edu.cs.byu.tweeter.shared.request.FeedRequest;
import edu.cs.byu.tweeter.shared.response.FeedResponse;

public class FeedService extends Service {

    public FeedResponse getFeedPage(FeedRequest request) throws IOException {
        ServerFacade server = getServerFacade();

        FeedResponse response = server.getFeedPage(request);
        if (response.isSuccess()) {
            for (Status status : response.getStati()) {
                loadImage(status.getAuthor());
            }
        }
        return response;
    }
}

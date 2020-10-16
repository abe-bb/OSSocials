package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;

public class FeedService extends Service {

    public FeedResponse getFeedPage(FeedRequest request) throws IOException {
        ServerFacade server = getServerFacade();

        FeedResponse response = server.getFeedPage(request);
        for (Status status : response.getStati()) {
            loadImage(status.getAuthor());
        }
        return response;
    }
}

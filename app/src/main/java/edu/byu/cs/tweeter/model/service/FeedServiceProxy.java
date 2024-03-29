package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;

public class FeedServiceProxy extends ServiceProxy {

    public FeedResponse getFeedPage(FeedRequest request) throws IOException, TweeterRemoteException {
        ServerFacade server = getServerFacade();

        request.setUser(stripImages(request.getUser()));

        FeedResponse response = server.getFeed(request);
        if (response.isSuccess()) {
            for (Status status : response.getStati()) {
                loadImage(status.getAuthor());
            }
        }
        return response;
    }
}

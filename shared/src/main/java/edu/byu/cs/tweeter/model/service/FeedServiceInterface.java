package edu.byu.cs.tweeter.model.service;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;

public interface FeedServiceInterface {
    FeedResponse getFeed(FeedRequest request) throws TweeterRemoteException;
}

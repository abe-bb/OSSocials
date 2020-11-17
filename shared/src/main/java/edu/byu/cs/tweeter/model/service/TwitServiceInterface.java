package edu.byu.cs.tweeter.model.service;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.TwitRequest;
import edu.byu.cs.tweeter.model.service.response.TwitResponse;

public interface TwitServiceInterface {
    TwitResponse sendTwit(TwitRequest request) throws TweeterRemoteException;
}

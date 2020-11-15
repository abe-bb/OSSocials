package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.request.FollowRequest;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.request.TwitRequest;
import edu.byu.cs.tweeter.model.service.request.UserDetailRequest;
import edu.byu.cs.tweeter.model.service.response.FollowResponse;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;
import edu.byu.cs.tweeter.model.service.response.TwitResponse;
import edu.byu.cs.tweeter.model.service.response.UserDetailResponse;

public class MainService extends ServiceProxy {

    public TwitResponse sendTwit(TwitRequest request) {
        ServerFacade server = getServerFacade();
        return server.sendTwit(request);
    }

    public UserDetailResponse getUserDetails(UserDetailRequest request) throws IOException {
        ServerFacade server = getServerFacade();
        UserDetailResponse response = server.getUserDetails(request);
        if (response.isSuccess()) {
            loadImage(response.getDetails().getViewee());
        }
        return response;
    }

    public FollowResponse follow(FollowRequest request) {
        ServerFacade server = getServerFacade();
        return server.follow(request);
    }

    public LogoutResponse logout(LogoutRequest request) {
        ServerFacade server = getServerFacade();
        LogoutResponse response = server.logout(request);
        return response;
    }



}

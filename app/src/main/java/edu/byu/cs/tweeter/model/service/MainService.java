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

public class MainService extends Service {
    private ServerFacade server;

    public MainService() {
        server = getServerFacade();
    }

    public TwitResponse sendTwit(TwitRequest request) {
        return server.sendTwit(request);
    }

    public UserDetailResponse getUserDetails(UserDetailRequest request) throws IOException {
        UserDetailResponse response = server.getUserDetails(request);
        loadImage(response.getDetails().getViewee());
        return response;
    }

    public FollowResponse follow(FollowRequest request) {
        return server.follow(request);
    }

    public LogoutResponse logout(LogoutRequest request) {
        return server.logout(request);
    }



}

package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.cs.byu.tweeter.shared.request.FollowRequest;
import edu.cs.byu.tweeter.shared.request.LogoutRequest;
import edu.cs.byu.tweeter.shared.request.TwitRequest;
import edu.cs.byu.tweeter.shared.request.UserDetailRequest;
import edu.cs.byu.tweeter.shared.response.FollowResponse;
import edu.cs.byu.tweeter.shared.response.LogoutResponse;
import edu.cs.byu.tweeter.shared.response.TwitResponse;
import edu.cs.byu.tweeter.shared.response.UserDetailResponse;

public class MainService extends Service {

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

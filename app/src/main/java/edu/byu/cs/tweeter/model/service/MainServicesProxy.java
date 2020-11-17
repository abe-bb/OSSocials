package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.FollowRequest;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.request.TwitRequest;
import edu.byu.cs.tweeter.model.service.request.UserDetailRequest;
import edu.byu.cs.tweeter.model.service.response.FollowResponse;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;
import edu.byu.cs.tweeter.model.service.response.TwitResponse;
import edu.byu.cs.tweeter.model.service.response.UserDetailResponse;

public class MainServicesProxy extends ServiceProxy implements TwitServiceInterface, UserDetailServiceInterface, LogoutServiceInterface, FollowServiceInterface {

    public TwitResponse sendTwit(TwitRequest request) throws TweeterRemoteException {
        Status status = request.getTwit();
        status.setAuthor(stripImages(status.getAuthor()));
        request.setTwit(status);
        ServerFacade server = getServerFacade();
        return server.sendTwit(request);
    }

    public UserDetailResponse getUserDetails(UserDetailRequest request) throws IOException, TweeterRemoteException {
        request.setViewee(stripImages(request.getViewee()));
        request.setViewer(stripImages(request.getViewer()));

        ServerFacade server = getServerFacade();
        UserDetailResponse response = server.getUserDetails(request);
        if (response.isSuccess()) {
            loadImage(response.getDetails().getViewee());
        }
        return response;
    }

    public FollowResponse follow(FollowRequest request) throws TweeterRemoteException {
        request.setFollowee(stripImages(request.getFollowee()));
        request.setFollower(stripImages(request.getFollower()));

        ServerFacade server = getServerFacade();
        return server.follow(request);
    }

    public LogoutResponse logout(LogoutRequest request) throws TweeterRemoteException {
        request.setLoggedInUser(stripImages(request.getLoggedInUser()));

        ServerFacade server = getServerFacade();
        LogoutResponse response = server.logout(request);
        return response;
    }



}

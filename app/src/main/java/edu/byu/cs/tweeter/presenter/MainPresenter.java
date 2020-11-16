package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.MainServicesProxy;
import edu.byu.cs.tweeter.model.service.request.FollowRequest;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.request.TwitRequest;
import edu.byu.cs.tweeter.model.service.request.UserDetailRequest;
import edu.byu.cs.tweeter.model.service.response.FollowResponse;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;
import edu.byu.cs.tweeter.model.service.response.TwitResponse;
import edu.byu.cs.tweeter.model.service.response.UserDetailResponse;

public class MainPresenter {
    private MainServicesProxy service;
    private View view;

    public MainPresenter(View view) {
        this.view = view;
        service = new MainServicesProxy();
    }


    public interface View {
    }

    public TwitResponse sendTwit(TwitRequest request) {
        MainServicesProxy service = getMainService();
        return service.sendTwit(request);
    }

    public UserDetailResponse getUserDetails(UserDetailRequest request) throws IOException {
        MainServicesProxy service = getMainService();
        return service.getUserDetails(request);
    }

    public FollowResponse follow(FollowRequest request) {
        MainServicesProxy service = getMainService();
        return service.follow(request);
    }

    public LogoutResponse logout(LogoutRequest request) throws TweeterRemoteException {
        MainServicesProxy service = getMainService();
        return service.logout(request);
    }

    MainServicesProxy getMainService() {
        if (service == null) {
            service = new MainServicesProxy();
        }
        return service;
    }
}

package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.MainService;
import edu.byu.cs.tweeter.model.service.request.FollowRequest;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.request.TwitRequest;
import edu.byu.cs.tweeter.model.service.request.UserDetailRequest;
import edu.byu.cs.tweeter.model.service.response.FollowResponse;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;
import edu.byu.cs.tweeter.model.service.response.TwitResponse;
import edu.byu.cs.tweeter.model.service.response.UserDetailResponse;

public class MainPresenter {
    private MainService service;
    private View view;

    public MainPresenter(View view) {
        this.view = view;
        service = new MainService();
    }


    public interface View {
    }

    public TwitResponse sendTwit(TwitRequest request) {
        MainService service = getMainService();
        return service.sendTwit(request);
    }

    public UserDetailResponse getUserDetails(UserDetailRequest request) throws IOException {
        MainService service = getMainService();
        return service.getUserDetails(request);
    }

    public FollowResponse follow(FollowRequest request) {
        MainService service = getMainService();
        return service.follow(request);
    }

    public LogoutResponse logout(LogoutRequest request) {
        MainService service = getMainService();
        return service.logout(request);
    }

    MainService getMainService() {
        if (service == null) {
            service = new MainService();
        }
        return service;
    }
}

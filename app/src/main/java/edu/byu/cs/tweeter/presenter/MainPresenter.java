package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.MainService;
import edu.cs.byu.tweeter.shared.request.FollowRequest;
import edu.cs.byu.tweeter.shared.request.LogoutRequest;
import edu.cs.byu.tweeter.shared.request.TwitRequest;
import edu.cs.byu.tweeter.shared.request.UserDetailRequest;
import edu.cs.byu.tweeter.shared.response.FollowResponse;
import edu.cs.byu.tweeter.shared.response.LogoutResponse;
import edu.cs.byu.tweeter.shared.response.TwitResponse;
import edu.cs.byu.tweeter.shared.response.UserDetailResponse;

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

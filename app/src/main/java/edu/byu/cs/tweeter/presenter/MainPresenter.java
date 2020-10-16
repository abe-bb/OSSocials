package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.MainService;
import edu.byu.cs.tweeter.model.service.request.FollowRequest;
import edu.byu.cs.tweeter.model.service.request.TwitRequest;
import edu.byu.cs.tweeter.model.service.request.UserDetailRequest;
import edu.byu.cs.tweeter.model.service.response.FollowResponse;
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
        return service.sendTwit(request);
    }

    public UserDetailResponse getUserDetails(UserDetailRequest request) throws IOException {
        return service.getUserDetails(request);
    }

    public FollowResponse follow(FollowRequest request) {
        return service.follow(request);
    }
}

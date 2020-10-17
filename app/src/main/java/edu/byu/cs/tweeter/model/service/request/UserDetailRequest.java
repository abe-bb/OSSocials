package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class UserDetailRequest {
    private final User viewee;
    private final User viewer;
    private final AuthToken token;

    public UserDetailRequest(User viewee, User viewer, AuthToken token) {
        this.viewee = viewee;
        this.viewer = viewer;
        this.token = token;
    }

    public User getViewee() {
        return viewee;
    }

    public User getViewer() {
        return viewer;
    }
}

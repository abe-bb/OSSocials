package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.User;

public class UserDetailRequest {
    User viewee;
    User viewer;

    public UserDetailRequest(User viewee, User viewer) {
        this.viewee = viewee;
        this.viewer = viewer;
    }

    public User getViewee() {
        return viewee;
    }

    public User getViewer() {
        return viewer;
    }
}

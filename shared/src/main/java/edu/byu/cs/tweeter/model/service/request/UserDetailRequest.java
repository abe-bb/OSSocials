package edu.byu.cs.tweeter.model.service.request;

import java.util.Objects;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class UserDetailRequest {
    private final User viewee;
    private final User viewer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailRequest request = (UserDetailRequest) o;
        return viewee.equals(request.viewee) &&
                viewer.equals(request.viewer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(viewee, viewer);
    }

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

package edu.byu.cs.tweeter.server.dao;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.domain.UserContextualDetails;

public class UserDetailDAO {
    public UserContextualDetails getUserDetails(User viewee, User viewer) {
        return new UserContextualDetails(viewee, (int) (Math.random() * (100000)), (int) (Math.random() * (100000)), false, viewer);
    }
}

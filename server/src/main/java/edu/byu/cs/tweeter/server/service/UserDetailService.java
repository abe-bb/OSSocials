package edu.byu.cs.tweeter.server.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.UserContextualDetails;
import edu.byu.cs.tweeter.model.domain.UserModel;
import edu.byu.cs.tweeter.model.service.UserDetailServiceInterface;
import edu.byu.cs.tweeter.model.service.request.UserDetailRequest;
import edu.byu.cs.tweeter.model.service.response.UserDetailResponse;
import edu.byu.cs.tweeter.server.dao.FollowsDAO;
import edu.byu.cs.tweeter.server.dao.UserDAO;
import edu.byu.cs.tweeter.server.dao.UserDetailDAO;

public class UserDetailService extends AuthenticatedService implements UserDetailServiceInterface {
    @Override
    public UserDetailResponse getUserDetails(UserDetailRequest request) {
        if (authorized(request.getViewer(), request.getToken())) {
            UserDAO userDAO = getUserDAO();
            FollowsDAO followsDAO = getFollowsDAO();

            boolean isFollowing = followsDAO.relationshipExists(request.getViewer(), request.getViewee());

            UserModel user  = userDAO.getUser(request.getViewee().getAlias());
            UserContextualDetails details = new UserContextualDetails(request.getViewee(), user.getNumFollowers(), user.getNumFollowing(), isFollowing, request.getViewer());
            return new UserDetailResponse(details);
        }
        else {
            return new UserDetailResponse("Unauthorized session! Please logout and log back in again");
        }
    }

    UserDetailDAO getUserDetailDAO() {
        return new UserDetailDAO();
    }

    UserDAO getUserDAO() {
        return new UserDAO();
    }

    FollowsDAO getFollowsDAO() {
        return new FollowsDAO();
    }
}

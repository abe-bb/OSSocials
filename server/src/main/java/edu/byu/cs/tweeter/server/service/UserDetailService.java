package edu.byu.cs.tweeter.server.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.UserContextualDetails;
import edu.byu.cs.tweeter.model.service.UserDetailServiceInterface;
import edu.byu.cs.tweeter.model.service.request.UserDetailRequest;
import edu.byu.cs.tweeter.model.service.response.UserDetailResponse;
import edu.byu.cs.tweeter.server.dao.UserDetailDAO;

public class UserDetailService implements UserDetailServiceInterface {
    @Override
    public UserDetailResponse getUserDetails(UserDetailRequest request) {
        UserDetailDAO dao = getUserDetailDAO();
        UserContextualDetails details = dao.getUserDetails(request.getViewee(), request.getViewer());
        return new UserDetailResponse(details);
    }

    UserDetailDAO getUserDetailDAO() {
        return new UserDetailDAO();
    }
}

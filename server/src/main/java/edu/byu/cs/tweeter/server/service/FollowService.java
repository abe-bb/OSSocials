package edu.byu.cs.tweeter.server.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.FollowServiceInterface;
import edu.byu.cs.tweeter.model.service.FollowersServiceInterface;
import edu.byu.cs.tweeter.model.service.request.FollowRequest;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.response.FollowResponse;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;
import edu.byu.cs.tweeter.server.dao.FollowDAO;
import edu.byu.cs.tweeter.server.dao.FollowsDAO;

public class FollowService extends AuthenticatedService implements FollowServiceInterface {
    @Override
    public FollowResponse follow(FollowRequest request) {
        if (authorized(request.getFollower(), request.getAuthToken())) {
            FollowsDAO followsDAO = getFollowsDAO();

            if (request.isUnfollow())
                followsDAO.removeFollow(request.getFollower(), request.getFollowee());
            else
                followsDAO.addFollow(request.getFollower(), request.getFollowee());

            return new FollowResponse(true, request.isUnfollow());
        }
        else {
            return new FollowResponse("Unauthorized session! Please logout and log back in again", request.isUnfollow());
        }
//        FollowDAO dao = getFollowDAO();
//        if (request.isUnfollow()) {
//            if (dao.unfollow(request.getFollowee(), request.getFollower()))
//                return new FollowResponse(true, true);
//            else {
//                return new FollowResponse("Error ocurred. Unable to perform unfollow operation.", true);
//            }
//        }
//        else {
//            if (dao.follow(request.getFollowee(), request.getFollower()))
//                return new FollowResponse(true, false);
//            else {
//                return new FollowResponse("Error ocurred. Unable to perform unfollow operation.", false);
//            }
//        }
    }

    FollowDAO getFollowDAO() {
        return new FollowDAO();
    }

    FollowsDAO getFollowsDAO() {
        return  new FollowsDAO();
    }


}

package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.FeedServiceInterface;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;
import edu.byu.cs.tweeter.server.dao.FeedDAO;
import edu.byu.cs.tweeter.server.dao.StoryDAO;

public class FeedService implements FeedServiceInterface {
    @Override
    public FeedResponse getFeed(FeedRequest request) {
        if (request.isStory()) {
            StoryDAO dao = getStoryDAO();
            return dao.getStory(request);
        }
        else {
            FeedDAO dao = getFeedDAO();
            return dao.getFeed(request);
        }
    }

    public FeedDAO getFeedDAO() {
        return new FeedDAO();
    }

    public StoryDAO getStoryDAO() {
        return new StoryDAO();
    }
}

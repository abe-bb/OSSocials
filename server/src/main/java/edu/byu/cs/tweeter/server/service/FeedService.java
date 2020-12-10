package edu.byu.cs.tweeter.server.service;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.model.domain.FeedStatusModel;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.StoryStatusModel;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.FeedServiceInterface;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;
import edu.byu.cs.tweeter.server.dao.FeedDAO;
import edu.byu.cs.tweeter.server.dao.StoryDAO;

public class FeedService extends AuthenticatedService implements FeedServiceInterface{
//    I had combined my feed and story services, before I designed my databases. This combined
//    design is suboptimal. Fix if time permits
    @Override
    public FeedResponse getFeed(FeedRequest request) {
        if (authorized(request.getUser(), request.getToken())) {
//            Story Request
            if (request.isStory()) {
                StoryDAO dao = getStoryDAO();
                List<StoryStatusModel> feed = dao.getStory(request.getUser(), request.getLastStatus());

                ArrayList<Status> statusPage = new ArrayList<>();

                boolean hasMorePages = false;
                for (StoryStatusModel status : feed) {
                    if (request.getLimit() <= statusPage.size()) {
                        hasMorePages = true;
                        break;
                    }

                    statusPage.add(status.getStatus());
                }

                return new FeedResponse(statusPage, hasMorePages);

            }
//            Feed Request
            else {
                FeedDAO dao = getFeedDAO();
                List<FeedStatusModel> feed = dao.getFeed(request.getUser(), request.getLastStatus());

                ArrayList<Status> statusPage = new ArrayList<>();

                boolean hasMorePages = false;
                for (FeedStatusModel status : feed) {
                    if (request.getLimit() <= statusPage.size()) {
                        hasMorePages = true;
                        break;
                    }

                    statusPage.add(status.getStatus());
                }

                return new FeedResponse(statusPage, hasMorePages);
            }
        }
        else {
            return new FeedResponse("Unauthorized session! Please logout and log back in again");
        }

    }

    FeedDAO getFeedDAO() {
        return new FeedDAO();
    }

    StoryDAO getStoryDAO() {
        return new StoryDAO();
    }
}

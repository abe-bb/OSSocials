package edu.byu.cs.tweeter.server.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;

import edu.byu.cs.tweeter.model.service.TwitServiceInterface;
import edu.byu.cs.tweeter.model.service.request.TwitRequest;
import edu.byu.cs.tweeter.model.service.response.TwitResponse;
import edu.byu.cs.tweeter.server.dao.StoryDAO;
import edu.byu.cs.tweeter.server.dao.TwitDAO;

public class TwitService extends AuthenticatedService implements TwitServiceInterface {
    private final String QUEUE_URL = "https://sqs.us-west-2.amazonaws.com/347657007310/StatusUpdateQueue";

    @Override
    public TwitResponse sendTwit(TwitRequest request) {
        if (authorized(request.getTwit().getAuthor(), request.getAuthToken())) {

//            Gson gson = new Gson();
//            String messageBody = gson.toJson(request.getTwit());

            SendMessageRequest msgRequest = new SendMessageRequest()
                    .withQueueUrl(QUEUE_URL)
                    .withMessageBody("Test");

            AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
            SendMessageResult result = sqs.sendMessage(msgRequest);

            StoryDAO storyDAO = getStoryDAO();
            storyDAO.addStatus(request.getTwit());
            return new TwitResponse(request.getTwit());
        }
        else {
            return new TwitResponse("Unauthorized session! Please logout and log back in again");
        }
    }

    private TwitDAO getTwitDAO() {
        return new TwitDAO();
    }

    StoryDAO getStoryDAO() {
        return new StoryDAO();
    }
}

package edu.byu.cs.tweeter.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.model.service.request.TwitRequest;
import edu.byu.cs.tweeter.model.service.response.TwitResponse;
import edu.byu.cs.tweeter.server.service.TwitService;

public class TwitHandler implements RequestHandler<TwitRequest, TwitResponse> {
    @Override
    public TwitResponse handleRequest(TwitRequest twitRequest, Context context) {
        TwitService service = new TwitService();

        return service.sendTwit(twitRequest);
    }
}

package edu.byu.cs.tweeter.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.model.service.request.UserDetailRequest;
import edu.byu.cs.tweeter.model.service.response.UserDetailResponse;
import edu.byu.cs.tweeter.server.service.UserDetailService;

public class UserDetailHandler implements RequestHandler<UserDetailRequest, UserDetailResponse> {


    @Override
    public UserDetailResponse handleRequest(UserDetailRequest request, Context context) {
        UserDetailService service = new UserDetailService();
        return service.getUserDetails(request);
    }
}

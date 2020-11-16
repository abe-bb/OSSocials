package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.request.UserDetailRequest;
import edu.byu.cs.tweeter.model.service.response.UserDetailResponse;

public interface UserDetailServiceInterface {
    UserDetailResponse getUserDetails(UserDetailRequest request) throws IOException;
}

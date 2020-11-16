package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.LogoutServiceInterface;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;
import edu.byu.cs.tweeter.server.dao.LogoutDAO;

public class LogoutService implements LogoutServiceInterface {
    @Override
    public LogoutResponse logout(LogoutRequest request) {
        LogoutDAO dao = getLogoutDAO();
        return dao.logout(request.getLoggedInUser(), request.getAuthToken());
    }

    private LogoutDAO getLogoutDAO() {
        return new LogoutDAO();
    }
}

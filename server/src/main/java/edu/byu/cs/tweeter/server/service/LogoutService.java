package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.LogoutServiceInterface;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;
import edu.byu.cs.tweeter.server.dao.AuthDAO;
import edu.byu.cs.tweeter.server.dao.LogoutDAO;

public class LogoutService implements LogoutServiceInterface {
    @Override
    public LogoutResponse logout(LogoutRequest request) {
        AuthDAO authDAO = getAuthDDAO();

        authDAO.removeToken(request.getLoggedInUser().getAlias(), request.getAuthToken().getToken());

        return new LogoutResponse(true);
    }

    LogoutDAO getLogoutDAO() {
        return new LogoutDAO();
    }

    AuthDAO getAuthDDAO() {
        return new AuthDAO();
    }
}

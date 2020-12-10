package edu.byu.cs.tweeter.server.service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.model.domain.AuthModel;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.server.dao.AuthDAO;

public class AuthenticatedService {
//    Inactive token lifetime in minutes;
    private final long inactiveTokenLifetime = 65;

    boolean authorized(User user, AuthToken token) {
        AuthDAO authDAO = getAuthDAO();

        ArrayList<AuthModel> toDelete = new ArrayList<>();
        List<AuthModel> tokens = authDAO.getTokens(user);

        Instant current = Instant.now();

        boolean authorized = false;

        for (AuthModel t : tokens) {
            Instant lastActive = Instant.parse(t.getLastUsed());
            Duration elapsedTime = Duration.between(lastActive, current);

//            Check if token is expired
            if (!elapsedTime.minusMinutes(inactiveTokenLifetime).isNegative()) {
                toDelete.add(t);
            }
//            Update last used time and authorize of token is valid
            else if (!authorized && t.getToken().equals(token.getToken())) {
                t.setLastUsed(current.toString());
                authDAO.updateToken(t);
                authorized = true;
            }
        }

        return authorized;
    }

    AuthDAO getAuthDAO() {
        return new AuthDAO();
    }
}

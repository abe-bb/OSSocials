package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.util.ByteArrayUtils;

public class ServiceProxy {
    /**
     * Loads the profile image data for each user in the iterable
     *
     * @param users the users to load profile images for
     */
    protected void loadImages(Iterable<User> users) throws IOException {
        for(User user : users) {
            byte [] bytes = ByteArrayUtils.bytesFromUrl(user.getImageUrl());
            user.setImageBytes(bytes);
        }
    }

    /**
     * Loads the profile image data for a single user.
     *
     * @param user the user whose profile image data is to be loaded.
     */
    protected static void loadImage(User user) throws IOException {
        if (user.getImageUrl() != null) {
            byte[] bytes = ByteArrayUtils.bytesFromUrl(user.getImageUrl());
            user.setImageBytes(bytes);
        }
    }


    /**
     * Returns an instance of {@link ServerFacade}. Allows mocking of the ServerFacade class for
     * testing purposes. All usages of ServerFacade should get their ServerFacade instance from this
     * method to allow for proper mocking.
     *
     * @return the instance.
     */
    ServerFacade getServerFacade() {
        return new ServerFacade();
    }

    protected User stripImages(User user) {
        if (user == null) {
            return null;
        }
        if (user.getImageBytes() == null) {
            return user;
        }
        User newUser = new User(user.getFirstName(), user.getLastName(), user.getAlias(), user.getImageUrl());
        return newUser;
    }
}

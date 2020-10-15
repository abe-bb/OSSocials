package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.util.ByteArrayUtils;

public class Service {
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
        byte [] bytes = ByteArrayUtils.bytesFromUrl(user.getImageUrl());
        user.setImageBytes(bytes);
    }
}

package edu.byu.cs.tweeter.server.dao;

import com.amazonaws.services.dynamodbv2.xspec.S;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import javax.jws.soap.SOAPBinding;

import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;

public class FeedDAOTest {

    @Test
    public void testAddStatus() {
        FeedDAO dao = new FeedDAO();

        Status status = new Status(new User("feedDAOTest"), Instant.now(), "Hello world!");

        dao.addStatus(status);
    }

    @Test
    public void testGetStatuses() {
        
    }

}

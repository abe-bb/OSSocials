package edu.byu.cs.tweeter.server.dao;

import org.junit.jupiter.api.Test;

import java.util.List;

import edu.byu.cs.tweeter.model.domain.Follow;
import edu.byu.cs.tweeter.model.domain.User;

public class FollowsDAOTest {

    @Test
    public void testGetFollowers() {
        FollowsDAO dao = new FollowsDAO();
        List<Follow> follows =  dao.getFollowers(new User("testSuper"), new User("test0"));

        int i = 0;
        for (Follow follow : follows) {
            System.out.println(follow.toString());
            if (i >= 9) {
                break;
            }
            i++;
        }
    }

    @Test
    public void testGetFollowees() {
        FollowsDAO dao = new FollowsDAO();
        List<Follow> follows = dao.getFollowees(new User("test1"), null);

        int i = 0;
        for (Follow follow : follows) {
            System.out.println(follow.toString());
            if (i >= 9) {
                break;
            }
            i++;
        }
    }

    @Test
    public void testAdd() {
        FollowsDAO dao = new FollowsDAO();
        User follower = new User("Jared", "Starr", "test3", "https://example.com");
        User followee = new User("Xavier", "Corel", "testSuper", "https://example.com");
        dao.addFollow(follower, followee);
    }

    @Test
    public void testRemove() {
        FollowsDAO dao = new FollowsDAO();
        User follower = new User("Jared", "Starr", "test3", "https://example.com");
        User followee = new User("Xavier", "Corel", "testSuper", "https://example.com");
        dao.removeFollow(follower, followee);
    }
}

package edu.byu.cs.tweeter.server.dao;

import org.junit.jupiter.api.Test;

import edu.byu.cs.tweeter.model.domain.User;

public class UserDAOTest {
    @Test
    public void testAddUser()  {
        UsersDAO dao = new UsersDAO();

        User user = new User("Abe", "Kloo", "abeKloo", "https://example.com");

        dao.addUser(user, "password", "salt");
    }

    @Test
    public void testGetUser() {
        UsersDAO dao = new UsersDAO();

        User user = new User("Abe", "Kloo", "abeKloo", "https://example.com");

        User result = dao.getUser("abeKlop");
        System.out.println(result.toString());
    }
}

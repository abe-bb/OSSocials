package edu.byu.cs.tweeter.server.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.domain.UserModel;

public class UsersDAO {

    void addUser(User user, String hashedPassword, String salt) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("us-west-2").build();
        DynamoDBMapper mapper = new DynamoDBMapper(client);

        UserModel userModel = new UserModel(user, hashedPassword, salt);
        mapper.save(userModel);
    }

    User getUser(String alias) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("us-west-2").build();
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        UserModel userModel = mapper.load(UserModel.class, alias);
        if (userModel == null) {
            return null;
        }
        return userModel.getUser();
    }
}

package edu.byu.cs.tweeter.model.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import edu.byu.cs.tweeter.model.domain.User;

@DynamoDBTable(tableName = "users")
public class UserModel {
    User user;
    String alias;
    String hashedPassword;
    String salt;
    int numFollowers;
    int numFollowing;

    public UserModel() {
    }

    public UserModel(User user, String hashedPassword, String salt) {
        this.user = user;
        this.alias = user.getAlias();
        this.hashedPassword = hashedPassword;
        this.salt = salt;
    }

    @DynamoDBAttribute
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @DynamoDBHashKey
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }

    @DynamoDBAttribute
    public String getHashedPassword() {
        return hashedPassword;
    }
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    @DynamoDBAttribute
    public String getSalt() {
        return salt;
    }
    public void setSalt(String salt) {
        this.salt = salt;
    }

    @DynamoDBAttribute
    public int getNumFollowers() {
        return numFollowers;
    }
    public void setNumFollowers(int numFollowers) {
        this.numFollowers = numFollowers;
    }

    @DynamoDBAttribute
    public int getNumFollowing() {
        return numFollowing;
    }
    public void setNumFollowing(int numFollowing) {
        this.numFollowing = numFollowing;
    }
}

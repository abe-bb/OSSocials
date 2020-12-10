package edu.byu.cs.tweeter.model.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "follows")
public class Follow {
    String follower_alias;
    String followee_alias;
    User follower;
    User followee;

    public Follow(User follower, User followee) {
        follower_alias = follower.getAlias();
        followee_alias = followee.getAlias();

        this.follower = follower;
        this.followee = followee;
    }

    public Follow() {
    }

    @DynamoDBHashKey
    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "followee_alias-follower_alias-index")
    public String getFollower_alias() {
        return follower_alias;
    }
    public void setFollower_alias(String follower_alias) {
        this.follower_alias = follower_alias;
    }

    @DynamoDBRangeKey
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "followee_alias-follower_alias-index")
    public String getFollowee_alias() {
        return followee_alias;
    }
    public void setFollowee_alias(String followee_alias) {
        this.followee_alias = followee_alias;
    }

    @DynamoDBAttribute
    public User getFollower() {
        return follower;
    }
    public void setFollower(User follower) {
        this.follower = follower;
    }

    @DynamoDBAttribute
    public User getFollowee() {
        return followee;
    }
    public void setFollowee(User followee) {
        this.followee = followee;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "follower_alias='" + follower_alias + '\'' +
                ", followee_alias='" + followee_alias + '\'' +
                ", follower=" + follower +
                ", followee=" + followee +
                '}';
    }
}

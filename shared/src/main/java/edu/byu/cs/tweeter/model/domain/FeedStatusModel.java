package edu.byu.cs.tweeter.model.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.UUID;

@DynamoDBTable(tableName = "feed")
public class FeedStatusModel {
    String sortKey;
    String alias;
    Status status;

    public FeedStatusModel(Status status) {
        this.alias = status.getAuthor().getAlias();
        this.status = status;
        this.sortKey = status.getTimeOfCreation() + status.getUuid();
    }

    public FeedStatusModel() {
    }

    @DynamoDBRangeKey
    public String getSortKey() {
        return sortKey;
    }
    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }

    @DynamoDBHashKey
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }

    @DynamoDBAttribute
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}

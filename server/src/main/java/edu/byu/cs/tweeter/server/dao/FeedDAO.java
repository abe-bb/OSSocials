package edu.byu.cs.tweeter.server.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import edu.byu.cs.tweeter.model.domain.FeedStatusModel;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;

public class FeedDAO {
    AmazonDynamoDB client;
    DynamoDBMapper mapper;

    public FeedDAO() {
        client = AmazonDynamoDBClientBuilder.standard().withRegion("us-west-2").build();
        mapper = new DynamoDBMapper(client);
    }

    public List<FeedStatusModel> getFeed(User user, Status lastStatus) {
        String condition = "#alias = :alias and #sortKey < :sortKey";

        HashMap<String, String> nameMap = new HashMap<>();
        nameMap.put("#alias", "alias");
        nameMap.put("#sortKey", "sortKey");

        HashMap<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":alias", new AttributeValue().withS(user.getAlias()));

        if (lastStatus == null) {
            valueMap.put(":alias", new AttributeValue().withS(" "));
        }
        else {
            valueMap.put(":alias", new AttributeValue().withS(lastStatus.getTimeOfCreation() + lastStatus.getUuid()));
        }

        DynamoDBQueryExpression<FeedStatusModel> expression = new DynamoDBQueryExpression<FeedStatusModel>()
                .withKeyConditionExpression(condition)
                .withExpressionAttributeNames(nameMap)
                .withExpressionAttributeValues(valueMap);

        return mapper.query(FeedStatusModel.class, expression);
    }

    public void addStatus(Status status) {
        FeedStatusModel statusModel = new FeedStatusModel(status);

        mapper.save(statusModel);
    }
}

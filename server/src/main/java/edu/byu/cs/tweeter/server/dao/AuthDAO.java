package edu.byu.cs.tweeter.server.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import org.joda.time.Instant;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import edu.byu.cs.tweeter.model.domain.AuthModel;
import edu.byu.cs.tweeter.model.domain.User;

public class AuthDAO {
    AmazonDynamoDB client;
    DynamoDBMapper mapper;

    public AuthDAO() {
        client = AmazonDynamoDBClientBuilder.standard().withRegion("us-west-2").build();
        mapper = new DynamoDBMapper(client);
    }

    public AuthModel addToken(User user) {
        AuthModel authModel = new AuthModel(user.getAlias(), UUID.randomUUID().toString(), Instant.now().toString());

        mapper.save(authModel);

        return authModel;

    }

    public void removeToken(String alias, String token) {
        AuthModel model = new AuthModel(alias, token, null);
        mapper.delete(model);
    }

    public void updateToken(AuthModel token) {
        mapper.save(token);

    }

    public List<AuthModel> getTokens(User user) {
        String condition = "#alias = :alias";

        HashMap<String, String> nameMap = new HashMap<>();
        nameMap.put("#alias", "alias");

        HashMap<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":alias", new AttributeValue().withS(user.getAlias()));

        DynamoDBQueryExpression<AuthModel> expression = new DynamoDBQueryExpression<AuthModel>()
                .withKeyConditionExpression(condition)
                .withExpressionAttributeNames(nameMap)
                .withExpressionAttributeValues(valueMap);

        return mapper.query(AuthModel.class, expression);
    }

    public void removeTokens(List<AuthModel> tokens) {
        mapper.batchDelete(tokens);
    }
}

package edu.byu.cs.tweeter.server.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Index;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import edu.byu.cs.tweeter.model.domain.Follow;
import edu.byu.cs.tweeter.model.domain.User;

public class FollowsDAO {
    public void addFollow(User follower, User followee) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("us-west-2").build();
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        Follow follow = new Follow(follower, followee);
        mapper.save(follow);

//        DeleteItemSpec deleteSpec = new DeleteItemSpec()
//                .withPrimaryKey("follower_alias", follower.getAlias(), "followee_alias", followee.getAlias());
//        HashMap<String, Object> itemMap = new HashMap<>();
//        itemMap.put("follower_alias", follower.getAlias());
//        itemMap.put("followee_alias", followee.getAlias());
//
//        PutItemOutcome outcome = table.putItem(new Item().withPrimaryKey("follower_alias", follower.getAlias(), "followee_alias", followee.getAlias()));
//        return true;
    }

    public void removeFollow(User follower, User followee) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("us-west-2").build();
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        Follow follow = new Follow(follower, followee);
        mapper.delete(follow);
//        DynamoDB db = new DynamoDB(client);
//
//        Table table = db.getTable("follows");
//
////        DeleteItemSpec deleteSpec = new DeleteItemSpec()
////                .withPrimaryKey("follower_alias", follower.getAlias(), "followee_alias", followee.getAlias());
//
//        table.deleteItem("follower_alias", follower.getAlias(), "followee_alias", followee.getAlias());
//        return true;
    }

    public List<Follow> getFollowers(User followee, User lastFollower) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("us-west-2").build();
        DynamoDBMapper mapper = new DynamoDBMapper(client);

        DynamoDBQueryExpression<Follow>  expression = new DynamoDBQueryExpression<Follow>();

        expression.withIndexName("followee_alias-follower_alias-index");
        String condition = "#followee_alias = :fee_alias and #follower_alias >= :catch_all";

        HashMap<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":fee_alias", new AttributeValue().withS(followee.getAlias()));


        HashMap<String, String> nameMap = new HashMap<>();
        nameMap.put("#followee_alias", "followee_alias");
        nameMap.put("#follower_alias", "follower_alias");

        if (lastFollower != null) {
            condition = "#followee_alias = :fee_alias and #follower_alias > :fer_alias";
            valueMap.put(":fer_alias", new AttributeValue().withS(lastFollower.getAlias()));
        }
        else {
            valueMap.put(":catch_all", new AttributeValue().withS(" "));
        }

        expression.withKeyConditionExpression(condition)
                .withExpressionAttributeNames(nameMap)
                .withExpressionAttributeValues(valueMap)
                .withConsistentRead(false);

        return mapper.query(Follow.class, expression);
    }

    public List<Follow> getFollowees(User follower, User lastFollowee) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("us-west-2").build();
        DynamoDBMapper mapper = new DynamoDBMapper(client);

        DynamoDBQueryExpression<Follow>  expression = new DynamoDBQueryExpression<Follow>();

//        expression.withIndexName("followee_alias-follower_alias-index");
        String condition = "#follower_alias = :fer_alias and #followee_alias >= :catch_all";

        HashMap<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":fer_alias", new AttributeValue().withS(follower.getAlias()));


        HashMap<String, String> nameMap = new HashMap<>();
        nameMap.put("#followee_alias", "followee_alias");
        nameMap.put("#follower_alias", "follower_alias");

        if (lastFollowee != null) {
            condition = "#follower_alias = :fer_alias and #followee_alias > :fee_alias";
            valueMap.put(":fee_alias", new AttributeValue().withS(lastFollowee.getAlias()));
        }
        else {
            valueMap.put(":catch_all", new AttributeValue().withS(" "));
        }

        expression.withKeyConditionExpression(condition)
                .withExpressionAttributeNames(nameMap)
                .withExpressionAttributeValues(valueMap)
                .withConsistentRead(false);

        return mapper.query(Follow.class, expression);
    }
//        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("us-west-2").build();
//        DynamoDB db = new DynamoDB(client);
//
//        Table table = db.getTable("follows");
//
//        HashMap<String, String> nameMap = new HashMap<>();
//        nameMap.put("#fer_alias", "follower_alias");
//
//        HashMap<String, Object> valueMap = new HashMap<>();
//        valueMap.put(":alias", follower.getAlias());
//
//        QuerySpec querySpec = new QuerySpec()
//                .withKeyConditionExpression("#fer_alias = :alias and ")
//                .withNameMap(nameMap)
//                .withValueMap(valueMap);
//
//        ItemCollection<QueryOutcome> items = table.query(querySpec);
//
//        Iterator<Item> iterator = items.iterator();
//        Item item;
//        for (item = iterator.next(); iterator.hasNext(); item = iterator.next()) {
//            System.out.println(item.toJSONPretty());
//        }
//        System.out.println(item.toJSONPretty());
//        return true;
//    }
}

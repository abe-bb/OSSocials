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
import edu.byu.cs.tweeter.model.domain.StoryStatusModel;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;

public class StoryDAO {
    public List<StoryStatusModel> getStory(User user, Status lastStatus) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("us-west-2").build();
        DynamoDBMapper mapper = new DynamoDBMapper(client);

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

        DynamoDBQueryExpression<StoryStatusModel> expression = new DynamoDBQueryExpression<StoryStatusModel>()
                .withKeyConditionExpression(condition)
                .withExpressionAttributeNames(nameMap)
                .withExpressionAttributeValues(valueMap);

        return mapper.query(StoryStatusModel.class, expression);
    }

    public void addStatus(Status status) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("us-west-2").build();
        DynamoDBMapper mapper = new DynamoDBMapper(client);

        StoryStatusModel statusModel = new StoryStatusModel(status);

        mapper.save(statusModel);
    }
//    // This is the hard coded data returned by the server Facade
//    private static final String MALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png";
//
//    private final User user1 = new User("Allen", "Anderson", MALE_IMAGE_URL);
//
//    private final Instant timeStamp = Instant.parse("2020-10-16T00:13:42.879Z");
//    //    story resonses
//    private final Status status21 = new Status(user1, timeStamp, "Google, where is the nearest Wendy's?");
//    private final Status status22 = new Status(user1, timeStamp, "Google, how long does Google take to answer?");
//    private final Status status23 = new Status(user1, timeStamp, "Google please, I don't feel like cooking. I just want Wendy's");
//    private final Status status24 = new Status(user1, timeStamp, "Google, how to fix google won't respond");
//    private final Status status25 = new Status(user1, timeStamp, "Google, why can my family see my google searches??????");
//
//    public FeedResponse getStory(FeedRequest request) {
//
//        List<Status> allStati;
//        allStati = getDummyStory();
//        List<Status> responseStati = new ArrayList<>(request.getLimit());
//
//        boolean hasMorePages = false;
//
//        if(request.getLimit() > 0) {
//            int statiIndex = getFeedStartingIndex(request.getLastStatus(), allStati);
//
//            for(int limitCounter = 0; statiIndex < allStati.size() && limitCounter < request.getLimit(); statiIndex++, limitCounter++) {
//                responseStati.add(allStati.get(statiIndex));
//            }
//
//            hasMorePages = statiIndex < allStati.size();
//        }
//
//        return new FeedResponse(responseStati, hasMorePages);
//    }
//
//    List<Status> getDummyStory() {
//        return Arrays.asList(status21, status22, status23, status24, status25);
//    }
//
//    private int getFeedStartingIndex(Status lastStatus, List<Status> allStati) {
//
//        int statiIndex = 0;
//
//        if (lastStatus != null) {
//            // This is a paged request for something after the first page. Find the first item
//            // we should return
//            for (int i = 0; i < allStati.size(); i++) {
//                if (lastStatus.equals(allStati.get(i))) {
//                    // We found the index of the last item returned last time. Increment to get
//                    // to the first one we should return
//                    statiIndex = i + 1;
//                    break;
//                }
//            }
//        }
//
//        return statiIndex;
//    }

}

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
    public List<FeedStatusModel> getFeed(User user, Status lastStatus) {
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

        DynamoDBQueryExpression<FeedStatusModel> expression = new DynamoDBQueryExpression<FeedStatusModel>()
                .withKeyConditionExpression(condition)
                .withExpressionAttributeNames(nameMap)
                .withExpressionAttributeValues(valueMap);

        return mapper.query(FeedStatusModel.class, expression);
    }

    public void addStatus(Status status) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("us-west-2").build();
        DynamoDBMapper mapper = new DynamoDBMapper(client);

        FeedStatusModel statusModel = new FeedStatusModel(status);

        mapper.save(statusModel);
    }
//    // This is the hard coded data returned by the server Facade
//    private static final String MALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png";
//    private static final String FEMALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png";
//
//    private final Instant timeStamp = Instant.parse("2020-10-16T00:13:42.879Z");
//
//    private final User user1 = new User("Allen", "Anderson", MALE_IMAGE_URL);
//    private final User user2 = new User("Amy", "Ames", FEMALE_IMAGE_URL);
//    private final User user3 = new User("Bob", "Bobson", MALE_IMAGE_URL);
//    private final User user4 = new User("Bonnie", "Beatty", FEMALE_IMAGE_URL);
//    private final User user5 = new User("Chris", "Colston", MALE_IMAGE_URL);
//    private final User user6 = new User("Cindy", "Coats", FEMALE_IMAGE_URL);
//    private final User user7 = new User("Dan", "Donaldson", MALE_IMAGE_URL);
//    private final User user8 = new User("Dee", "Dempsey", FEMALE_IMAGE_URL);
//    private final User user9 = new User("Elliott", "Enderson", MALE_IMAGE_URL);
//    private final User user10 = new User("Elizabeth", "Engle", FEMALE_IMAGE_URL);
//    private final User user11 = new User("Frank", "Frandson", MALE_IMAGE_URL);
//    private final User user12 = new User("Fran", "Franklin", FEMALE_IMAGE_URL);
//    private final User user13 = new User("Gary", "Gilbert", MALE_IMAGE_URL);
//    private final User user14 = new User("Giovanna", "Giles", FEMALE_IMAGE_URL);
//    private final User user15 = new User("Henry", "Henderson", MALE_IMAGE_URL);
//    private final User user16 = new User("Helen", "Hopwell", FEMALE_IMAGE_URL);
//    private final User user17 = new User("Igor", "Isaacson", MALE_IMAGE_URL);
//    private final User user18 = new User("Isabel", "Isaacson", FEMALE_IMAGE_URL);
//    private final User user19 = new User("Justin", "Jones", MALE_IMAGE_URL);
//    private final User user20 = new User("Jill", "Johnson", FEMALE_IMAGE_URL);
//
//    //    feed responses
//    private final Status status1 = new Status(user2, timeStamp, "Good Morning!");
//    private final Status status2 = new Status(user2, timeStamp, "Why, sweet child. Why?");
//    private final Status status3 = new Status(user3, timeStamp, "Hello Tweeter!");
//    private final Status status4 = new Status(user4, timeStamp, "ngl, this is slurpalicious");
//    private final Status status5 = new Status(user5, timeStamp, "Hey @BonnieBeatty! What's slurpalicious?");
//    private final Status status6 = new Status(user6, timeStamp, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus blandit posuere justo, id ultricies ante aliquam in. Sed commodo felis tortor. Maecenas placerat ipsum quis laoreet congue. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Ut elementum in ante sit amet pretium. Aliquam id nisl blandit, rhoncus leo vel, tempor massa. Etiam id eros lorem. Morbi at eleifend erat. Aliquam pretium ac odio id maximus.\n" +
//            "\n" +
//            "Aenean accumsan, velit maximus congue scelerisque, sem sapien dictum sem, in accumsan augue nisl id orci. Morbi quis venenatis leo. Mauris pharetra augue sit amet sapien tincidunt volutpat. Ut dapibus accumsan ante, sit amet pellentesque libero hendrerit sed. Nunc quis justo quis massa bibendum viverra ac ut ligula. Vivamus vestibulum convallis accumsan. Donec lectus quam, ornare id placerat vitae, mollis vitae leo. Duis ultricies nunc vel diam imperdiet placerat vel ac ligula. Mauris rutrum orci odio, ut varius nisi sagittis ac. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.\n" +
//            "\n" +
//            "Aenean cursus, magna a pulvinar tempus, enim ligula sodales leo, eu aliquam urna erat vel dolor. Nullam nec lectus vulputate, maximus nibh quis, cursus diam. Ut ut mi gravida, dictum eros eu, varius nisl. Morbi mollis enim diam, eu elementum nunc consectetur sit amet. Cras non interdum massa, quis sollicitudin nisi. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Donec ut elit a magna interdum rhoncus ac nec turpis.\n" +
//            "\n" +
//            "Aenean vel eros eu nisi efficitur pulvinar. Vestibulum sagittis dolor nunc. Suspendisse sit amet ex ac quam mattis consequat ut id lectus. In hac habitasse platea dictumst. Vestibulum nec lacus a est posuere dignissim et a mi. Duis scelerisque diam vitae pretium maximus. Nunc luctus arcu in nunc commodo volutpat. Praesent imperdiet ligula justo. Suspendisse bibendum maximus justo in auctor. In non pulvinar purus. Nam in mauris varius ex dictum tempus eu a ante.\n" +
//            "\n" +
//            "Nunc scelerisque in ipsum sit amet dictum. Duis eget nunc at libero facilisis egestas. Vivamus at fringilla magna, quis mattis est. Pellentesque mattis orci id enim ullamcorper, vitae tincidunt arcu suscipit. Cras eget ligula sem. Aliquam erat volutpat. Suspendisse nec condimentum nisl, id efficitur est. In vestibulum sodales nulla, sit amet varius elit blandit a. Vestibulum et tincidunt nibh.");
//    private final Status status7 = new Status(user7, timeStamp, "@CindyCoats. Stop.");
//    private final Status status8 = new Status(user8, timeStamp, "I'm ");
//    private final Status status9 = new Status(user9, timeStamp, "too ");
//    private final Status status10 = new Status(user10, timeStamp, "sexy ");
//    private final Status status11 = new Status(user11, timeStamp, "for ");
//    private final Status status12 = new Status(user12, timeStamp, "my ");
//    private final Status status13 = new Status(user13, timeStamp, "shirt. ");
//    private final Status status14 = new Status(user14, timeStamp, "Just ");
//    private final Status status15 = new Status(user15, timeStamp, "too ");
//    private final Status status16 = new Status(user16, timeStamp, "sexy! ");
//    private final Status status17 = new Status(user17, timeStamp, "Life ");
//    private final Status status18 = new Status(user18, timeStamp, "Before ");
//    private final Status status19 = new Status(user19, timeStamp, "Death ");
//    private final Status status20 = new Status(user20, timeStamp, "!!!!!!");
//
//    //    story resonses
//    private final Status status21 = new Status(user1, timeStamp, "Google, where is the nearest Wendy's?");
//    private final Status status22 = new Status(user1, timeStamp, "Google, how long does Google take to answer?");
//    private final Status status23 = new Status(user1, timeStamp, "Google please, I don't feel like cooking. I just want Wendy's");
//    private final Status status24 = new Status(user1, timeStamp, "Google, how to fix google won't respond");
//    private final Status status25 = new Status(user1, timeStamp, "Google, why can my family see my google searches??????");
//
//    public FeedResponse getFeed(FeedRequest request) {
//
//        List<Status> allStati;
//        allStati = getDummyFeed();
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
//
//    List<Status> getDummyFeed() {
//        return Arrays.asList(status1, status2, status3, status4, status5, status6, status7, status8,
//                status9, status10, status11, status12, status13, status14, status15,
//                status16, status17, status18, status19, status20);
//    }
}

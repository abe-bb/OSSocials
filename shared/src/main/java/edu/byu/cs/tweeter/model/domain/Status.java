package edu.byu.cs.tweeter.model.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@DynamoDBDocument
public class Status {
    private User author;
    private String timeOfCreation;
    private String text;
    private String uuid;

    /**
     * Constructor for a Status
     * @param author the user who authored this status
     * @param timeOfCreation the moment in time that this status was authored
     * @param text the text of the status
     */
    public Status(User author, Instant timeOfCreation, String text) {
        this.author = author;
        this.timeOfCreation = timeOfCreation.toString();
        this.text = text;
        uuid = UUID.randomUUID().toString();
    }

    public Status() {}

    @DynamoDBAttribute
    public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
        this.author = author;
    }

    @DynamoDBAttribute
    public String getTimeOfCreation() {
        return timeOfCreation;
    }
    public void setTimeOfCreation(String timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
    }

    @DynamoDBAttribute
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    @DynamoDBAttribute
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return Objects.equals(author, status.author) &&
                Objects.equals(timeOfCreation, status.timeOfCreation) &&
                Objects.equals(text, status.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, timeOfCreation, text);
    }
}

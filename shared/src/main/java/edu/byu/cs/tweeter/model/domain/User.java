package edu.byu.cs.tweeter.model.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a user in the system.
 */
@DynamoDBDocument
public class User implements Comparable<User>, Serializable {

    String firstName;
    String lastName;
    String alias;
    String imageUrl;
    byte [] imageBytes;

    public User() {
    }

    public User(String alias) {
        firstName = null;
        lastName = null;
        this.alias = alias;
        imageUrl = null;
    }

    public User(String firstName, String lastName, String imageURL) {
        this(firstName, lastName, String.format("@%s%s", firstName, lastName), imageURL);
    }

    public User(String firstName, String lastName, String alias, String imageURL) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.alias = alias;
        this.imageUrl = imageURL;
    }

    public String fullName() {
        return String.format("%s %s", firstName, lastName);
    }


    @DynamoDBIgnore
    public byte [] getImageBytes() {
        return imageBytes;
    }
    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return alias.equals(user.alias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alias);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", alias='" + alias + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    @Override
    public int compareTo(User user) {
        return this.getAlias().compareTo(user.getAlias());
    }

    @DynamoDBAttribute
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @DynamoDBAttribute
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @DynamoDBAttribute
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }

    @DynamoDBAttribute
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

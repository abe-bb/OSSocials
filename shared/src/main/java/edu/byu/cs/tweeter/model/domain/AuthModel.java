package edu.byu.cs.tweeter.model.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "auth")
public class AuthModel {
    String alias;
    String token;
    String lastUsed;

    public AuthModel(String alias, String token, String lastUsed) {
        this.alias = alias;
        this.token = token;
        this.lastUsed = lastUsed;
    }

    public AuthModel() {
    }

    @DynamoDBHashKey
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }

    @DynamoDBRangeKey
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    @DynamoDBAttribute
    public String getLastUsed() {
        return lastUsed;
    }
    public void setLastUsed(String lastUsed) {
        this.lastUsed = lastUsed;
    }
}

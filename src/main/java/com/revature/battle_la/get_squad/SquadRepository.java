package com.revature.battle_la.get_squad;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SquadRepository {

    private final DynamoDBMapper dbReader;

    public SquadRepository() {
        dbReader = new DynamoDBMapper(AmazonDynamoDBClientBuilder.defaultClient());
    }

    public boolean createBro(Bro bro) {

        try {
            dbReader.save(bro);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}

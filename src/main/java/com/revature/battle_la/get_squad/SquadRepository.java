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

    public List<Bro> getSquad() {
        return dbReader.scan(Bro.class, new DynamoDBScanExpression());
    }

    public Optional<Bro> getBroByName(String name) {

        Map<String, AttributeValue> queryInputs = new HashMap<>();
        queryInputs.put(":name", new AttributeValue().withS(name));

        DynamoDBScanExpression query = new DynamoDBScanExpression()
                                            .withFilterExpression("name = :name")
                                            .withExpressionAttributeValues(queryInputs);

        List<Bro> queryResult = dbReader.scan(Bro.class, query);

        return queryResult.stream().findFirst();

    }

}

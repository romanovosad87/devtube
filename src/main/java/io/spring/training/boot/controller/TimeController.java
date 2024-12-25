package io.spring.training.boot.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TimeController implements TimeApi {

    private final DynamoDbClient dynamoDbClient;

    public TimeController(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public String getTime() {
        return LocalDateTime.now().toString();
    }

    public void saveUser(@RequestBody User user) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("id", AttributeValue.builder().s(user.id()).build());
        item.put("name", AttributeValue.builder().s(user.name()).build());

        // Create the PutItemRequest
        PutItemRequest request = PutItemRequest.builder()
                .tableName("User") // Specify your DynamoDB table name
                .item(item)
                .build();

        dynamoDbClient.putItem(request);
    }

}
record User(String id, String name){}

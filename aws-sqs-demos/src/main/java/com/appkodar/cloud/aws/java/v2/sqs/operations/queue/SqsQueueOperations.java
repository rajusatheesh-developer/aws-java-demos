package com.appkodar.cloud.aws.java.v2.sqs.operations.queue;


import com.appkodar.cloud.aws.java.v2.sqs.AwsClientUtils;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class SqsQueueOperations implements QueueOperations {

    public SqsClient getSqsClient() {
        return sqsClient;
    }

    private final SqsClient sqsClient;

    public SqsQueueOperations() {
        this.sqsClient = AwsClientUtils.getSQSqsClient();
    }

    public String createQueue(String queueName) {
        log.info("Creating queue with name :{}", queueName);
        return Optional.ofNullable(sqsClient.createQueue(CreateQueueRequest.builder()
                .queueName(queueName)
                .build()))
                .map(CreateQueueResponse::queueUrl)
                .stream().peek(url->System.out.println("Queue created with url : "+url))
                .filter(Objects::nonNull)
                .findFirst().orElse(null);
    }

    public boolean deleteQueue(String queueName) {
        return Optional.ofNullable(sqsClient.deleteQueue(
                DeleteQueueRequest.builder()
                        .queueUrl(getQueueUrl(queueName))
                        .build()))
                .filter(Objects::nonNull)
                .map(obj->Boolean.TRUE)
                .stream().findFirst()
                .orElseGet(()->Boolean.FALSE);
    }

    @Override
    public String getQueueUrl(final String queueName) {
        return Optional.ofNullable(sqsClient
                .getQueueUrl(GetQueueUrlRequest.builder()
                        .queueName(queueName)
                        .build()))
                .map(GetQueueUrlResponse::queueUrl)
                .stream()
                .peek(url->System.out.println("Queue found with url : "+url))
                .findFirst().orElse(null);
    }

    public List<String> listQueues() {
        log.info("getting all queues");
        return Optional.ofNullable(sqsClient.listQueues())
                .filter(ListQueuesResponse::hasQueueUrls)
                .map(ListQueuesResponse::queueUrls)
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

    }
}

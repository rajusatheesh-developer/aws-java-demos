package com.appkodar.cloud.aws.java.v2.sqs.operations.queue;

import java.util.List;

public interface QueueOperations
{
     String createQueue(String queueName);
     boolean deleteQueue(String queueName);
     String getQueueUrl(String queueName);
     List<String> listQueues();

}

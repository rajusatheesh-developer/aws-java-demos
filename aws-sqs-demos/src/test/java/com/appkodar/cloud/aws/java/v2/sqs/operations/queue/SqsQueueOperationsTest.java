package com.appkodar.cloud.aws.java.v2.sqs.operations.queue;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import software.amazon.awssdk.services.sqs.model.QueueDoesNotExistException;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SqsQueueOperationsTest {

    static SqsQueueOperations sqsQueueOperations;

    String queueName = "test-queue-one";
    String invalidQueueName = "invalid-queue";

    @BeforeAll
    static void setUp() throws Exception {
        sqsQueueOperations = new SqsQueueOperations();
    }

    @Test
    public void whenInitializedAWSService_ThenNotNull() {
        Assertions.assertNotNull(sqsQueueOperations.getSqsClient());
    }

    @Test
    @Order(1)
    public void should_create_NewQueue() {
        String queueUrl = sqsQueueOperations.createQueue(queueName);
        assertThat(queueUrl, containsString(queueName));
    }

    @Test
    @Order(2)
    public void should_get_queue_url_forNewQueue() {
        String queueUrl = sqsQueueOperations.getQueueUrl(queueName);
        assertThat(queueUrl, containsString(queueName));
    }

    @Test
    @Order(3)
    public void should_list_queues_withNewQueue() {
        List<String> queueUrlList = sqsQueueOperations.listQueues();
        assertThat(queueUrlList, contains(queueName));
    }

    @Test
    @Order(4)
    public void should_delete_queue() {
        boolean queueUrl = sqsQueueOperations.deleteQueue(queueName);
        assertThat(queueUrl, equalTo(true));
    }

    @Test
    @Order(5)
    public void should_get_queue_url_throwException_forInvalidQueue() {
        Assertions.assertThrows(QueueDoesNotExistException.class, ()
                -> sqsQueueOperations.getQueueUrl(invalidQueueName));
    }

    @Test
    @Order(6)
    public void should_delete_queue_throwException_forInvalidQueue() {
        Assertions.assertThrows(QueueDoesNotExistException.class, ()
                -> sqsQueueOperations.deleteQueue(invalidQueueName));
    }

}
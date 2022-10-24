package com.appkodar.cloud.aws.java.v2.sqs;


import com.appkodar.cloud.aws.java.v2.sqs.operations.queue.QueueOperations;
import com.appkodar.cloud.aws.java.v2.sqs.operations.queue.SqsQueueOperations;

import java.util.Scanner;

public class SQSMain {

    public static void main(String[] args) {
        showConsole();
    }

    public static void showConsole() {
        QueueOperations queueOperations = new SqsQueueOperations();
        System.out.println("AWS SQS Queue Operations Console");
        System.out.println("----------------------------------");
        System.out.println("1.Create Queue");
        System.out.println("2.List Queues");
        System.out.println("3.Delete Queue");
        System.out.println("4.Queue URL");
        System.out.println("----------------------------------");
        System.out.println("Enter ops code : ");
        Scanner scanner = new Scanner(System.in);
        int operation = scanner.nextInt();
        switch (operation) {
            case 1:
                String queueName = scanner.next();
                queueOperations.createQueue(queueName);
                break;
            case 2:
                queueOperations.listQueues();
                break;
            case 3:
                queueName = scanner.next();
                queueOperations.deleteQueue(queueName);
                break;
            case 4:
                queueName = scanner.next();
                queueOperations.getQueueUrl(queueName);
                break;
        }

    }

}

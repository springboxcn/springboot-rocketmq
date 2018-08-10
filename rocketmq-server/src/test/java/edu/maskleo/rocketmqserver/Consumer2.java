package edu.maskleo.rocketmqserver;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Consumer2 {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("example_group_name");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("TopicTestJ", "TagA || TagB || TagC");
        consumer.registerMessageListener(new MyMessageListenerOrderly());
        consumer.start();
        System.out.println("Consumer Started.");
    }

    static class MyMessageListenerOrderly implements MessageListenerOrderly {
        AtomicLong consumeTimes = new AtomicLong(0);

        public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
            context.setAutoCommit(false);
            /*System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + msgs + "%n");*/
            for (MessageExt e : msgs) {
                System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + new String(e.getBody()) + "%n");
            }
            return ConsumeOrderlyStatus.SUCCESS;
        }
    }

}

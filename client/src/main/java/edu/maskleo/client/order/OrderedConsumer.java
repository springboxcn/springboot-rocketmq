package edu.maskleo.client.order;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

public class OrderedConsumer {

    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("example_group_name");
        consumer.setNamesrvAddr("118.24.153.159:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("TopicTestjjj", "*");
        consumer.registerMessageListener((MessageListenerOrderly) (msgs,
                                                                   context) -> {
            // http://lifestack.cn/archives/483.html
            context.setAutoCommit(true);
            for (MessageExt msg : msgs) {
                System.out.println(msg);
            }
            return ConsumeOrderlyStatus.SUCCESS;
        });
        consumer.start();
        System.out.printf("Consumer Started.%n");
    }
}
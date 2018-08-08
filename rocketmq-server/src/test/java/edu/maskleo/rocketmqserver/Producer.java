package edu.maskleo.rocketmqserver;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.message.Message;

import java.util.Date;
import java.util.Random;

public class Producer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("mq-group");
        producer.setNamesrvAddr("127.0.0.1:9876");
       /* producer.setInstanceName("producer");*/
        producer.start();
        try {
            Random random = new Random();
            for (int i = 0; i < 100; i++) {
                Thread.sleep(random.nextInt(100));
                Message message = new Message("TopicA-test1", "TagA1", (new Date() + " Hello RocketMQ ,QuickStart" + i).getBytes());
                producer.send(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }

}
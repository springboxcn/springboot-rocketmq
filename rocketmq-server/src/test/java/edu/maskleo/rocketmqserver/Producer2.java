package edu.maskleo.rocketmqserver;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.Date;
import java.util.List;

public class Producer2 {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("example_group_name");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setVipChannelEnabled(false);
        producer.start();
        String[] tags = new String[]{"TagA", "TagB", "TagC"};
        try {
            for (int i = 0; i < 100; i++) {
                int orderId = i % 10;
                Message message = new Message("TopicTestJ", tags[i % tags.length], "k" + i, (new Date() + " Hello RocketMQ ,QuickStart" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sr = producer.send(message, new MessageQueueSelector() {
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer) arg;
                        int index = id % mqs.size();
                        return mqs.get(index);
                    }
                }, orderId);
                System.out.println(sr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }


}
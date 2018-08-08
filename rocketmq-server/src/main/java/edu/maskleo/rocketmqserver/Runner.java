package edu.maskleo.rocketmqserver;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @Override
    public void run(String... args) throws Exception {
        /*System.out.println("Sending message...");
        Message msg = new Message("sms1",// topic
                "verifycode1",// tag
                "KKK",//key用于标识业务的唯一性
                ("Hello RocketMQ !!!!!!!!!!").getBytes()// body 二进制字节数组
        );
        SendResult result = defaultMQProducer.send(msg);
        System.out.println(System.currentTimeMillis() + ":" + result);*/
    }

}

package edu.maskleo.rocketmqserver;

import com.alibaba.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

@Component
public class Receiver implements MessageProcessor {

    @Override
    public boolean handleMessage(MessageExt messageExt) {
        System.out.println(System.currentTimeMillis() + ":receive : " + messageExt.toString());
        return true;
    }
}
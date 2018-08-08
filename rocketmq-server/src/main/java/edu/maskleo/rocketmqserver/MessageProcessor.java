package edu.maskleo.rocketmqserver;

import com.alibaba.rocketmq.common.message.MessageExt;

public interface MessageProcessor {

    /**
     * 处理消息的接口
     *
     * @param messageExt
     * @return
     */
    boolean handleMessage(MessageExt messageExt);

}

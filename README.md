# springboot-rocketmq

## download

- [release-notes-4.3.0](http://rocketmq.apache.org/release_notes/release-notes-4.3.0/)

## 准备工作

- ROCKETMQ_HOME
- 启动 mqnamesrv `mqnamesrv.cmd`
- 启动 broker  `./mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true`

## 编码

- 生产者

```java
DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("rmq-group");
consumer.setNamesrvAddr("127.0.0.1:9876");
consumer.setInstanceName("consumer");
consumer.subscribe("TopicA-test", "TagA");
consumer.registerMessageListener(new MessageListenerConcurrently() {
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(
            List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        for (MessageExt msg : msgs) {
            System.out.println(new String(msg.getTopic()));
            System.out.println(new String(msg.getTags()));
            System.out.println("=== " + new String(msg.getBody()));
        }

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
});
consumer.start();
System.out.println("Consumer Started.");
```


- 消费者

```java
DefaultMQProducer producer = new DefaultMQProducer("mq-group");
producer.setNamesrvAddr("127.0.0.1:9876");
producer.setInstanceName("producer");
producer.start();
try {
    Random random = new Random();
    for (int i = 0; i < 100; i++) {
        Thread.sleep(random.nextInt(100));
        Message message = new Message("TopicA-test", "TagA", (new Date() + " Hello RocketMQ ,QuickStart" + i).getBytes());
        producer.send(message);
    }
} catch (Exception e) {
    e.printStackTrace();
}
producer.shutdown();
```

## 其他

- `CONSUME_FROM_TIMESTAMP`//从某个时间点开始消费，和 `setConsumeTimestamp()` 配合使用，默认是半个小时以前


## 参考

- https://blog.csdn.net/hsl_1990_08_15/article/details/80077552
- http://jm.taobao.org/2017/01/12/rocketmq-quick-start-in-10-minutes/
- [RocketMQ消费者，设置setConsumeFromWhere无效的问题](https://blog.csdn.net/scutshuxue/article/details/51694334)
- [RocketMQ系列文章](http://lifestack.cn/archives/tag/rocketmq)
- [RocketMQ原理解析-Consumer](http://technoboy.iteye.com/blog/2368553)
- https://blog.csdn.net/lovesomnus/article/details/51776942
- https://www.cnblogs.com/sunshine-2015/p/6295100.html
- [RocketMQ事务消费和顺序消费详解](https://www.cnblogs.com/520playboy/p/6750023.html)
- https://segmentfault.com/a/1190000009919454
- http://valleylord.github.io/post/201607-mq-rocketmq/
- [RocketMQ——消息ACK机制及消费进度管理](http://jaskey.github.io/blog/2017/01/25/rocketmq-consume-offset-management/)
- [ResetOffsetByTimeCommand](https://github.com/apache/rocketmq/blob/0c5e53db6f4d0ed9f25747379a8b679e2da5392d/tools/src/main/java/org/apache/rocketmq/tools/command/offset/ResetOffsetByTimeCommand.java)





- [java消息中间件 RocketMQ Linux安装与运行](https://www.cnblogs.com/adamJin/p/6897807.html?utm_source=itdadao&utm_medium=referral)
- [解决RocketMQ报No route info of this topic：异常](https://blog.csdn.net/zknxx/article/details/52987216)
- [linux下RocketMQ的安装](https://www.jianshu.com/p/912701cf1705)

`nohup sh mqnamesrv >/var/log/ns.log &`
`nohup sh mqbroker  -n 127.0.0.1:9876 autoCreateTopicEnable=true > /var/log/mq.log 2>&1 &`

```shell
nohup sh mqnamesrv >/var/log/ns.log &
nohup sh mqbroker -n 127.0.0.1:9876 -c ../conf/broker.c autoCreateTopicEnable=true > /var/log/mq.log 2>&1 &

telnet 127.0.0.1 9876
```

https://rocketmq.apache.org/docs/order-example/

## 其他示例

- https://www.programcreek.com/java-api-examples/?class=com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer&method=subscribe

- https://blog.csdn.net/jenyzhang/article/details/75035802

- https://github.com/apache/rocketmq-externals/blob/master/rocketmq-spring-boot-starter/README_zh_CN.md

## LICENSE

![](LICENSE.png)

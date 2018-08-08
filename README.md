# springboot-rocketmq

## download

- [release-notes-4.3.0](http://rocketmq.apache.org/release_notes/release-notes-4.3.0/)

## 系统环境变量

- ROCKETMQ_HOME

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


## 参考

- https://blog.csdn.net/hsl_1990_08_15/article/details/80077552
- http://jm.taobao.org/2017/01/12/rocketmq-quick-start-in-10-minutes/

## LICENSE

![](LICENSE.png)

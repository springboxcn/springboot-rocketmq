# springboot-rocketmq

## TODO

-[ ] 完善示例
-[ ] 控制台参数相关补充
-[ ] `rocketmq` `rabbitmq` `activemq` `kafka` 区别 


## 使用方式

- [无序](client/src/main/java/edu/maskleo/client/simple)
- [有序](client/src/main/java/edu/maskleo/client/order)

## 下载

- [rocketmq-all-4.2.0-bin-release.zip](https://www.apache.org/dyn/closer.cgi?path=rocketmq/4.2.0/rocketmq-all-4.2.0-bin-release.zip)

## 安装

- 安装 `JDK`
- 解压 `rocketmq-all-4.2.0-bin-release.zip` , 配置环境变量 `ROCKETMQ_HOME`

## 可能出现的问题以及解决方案

- 提示内存不足以分配

    ```
    Java HotSpot(TM) 64-Bit Server VM warning: INFO: os::commit_memory(0x00000005c0000000, 8589934592, 0) failed; error='Cannot allocate memory' (errno=12)
    ```
  调整参数 `%ROCKETMQ_HOME%/bin` 目录下 `runbroker.sh` 和 `runserver.sh`
  
  ```shell
  vi runbroker.sh/
  JAVA_OPT="${JAVA_OPT} -server -Xms256m -Xmx256m -Xmn256m"
  JAVA_OPT="${JAVA_OPT} -XX:MaxDirectMemorySize=256m"
    
  vi runserver.sh
  JAVA_OPT="${JAVA_OPT} -server -Xms128m -Xmx128m -Xmn128m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m"
  ```

- 出现 `connect to 172.17.42.1:10909 failed`

  ```java
  producer.setVipChannelEnabled(false);
  ```

- 出现 `connect to 172.17.42.1:10911 failed`, 修改 `broker.conf` 启动文件

  ```
    namesrvAddr=127.0.0.1:9876
    brokerIP1=192.168.143.128
    brokerName=localhost
    brokerClusterName=DefaultCluster
    brokerId=0
    autoCreateTopicEnable=true
    autoCreateSubscriptionGroup=true
    rejectTransactionMessage=false
    fetchNamesrvAddrByAddressServer=false
    storePathRootDir=/root/store
    storePathCommitLog=/root/store/commitlog
    flushIntervalCommitLog=1000
    commitIntervalCommitLog=1000
    flushCommitLogTimed=false
    deleteWhen=04
    fileReservedTime=72
    maxTransferBytesOnMessageInMemory=262144
    maxTransferCountOnMessageInMemory=32
    maxTransferBytesOnMessageInDisk=65536
    maxTransferCountOnMessageInDisk=8
    accessMessageInMemoryMaxRatio=40
    messageIndexEnable=true
    messageIndexSafe=false
    haMasterAddress=
    brokerRole=ASYNC_MASTER
    flushDiskType=ASYNC_FLUSH
    cleanFileForciblyEnable=true
    transientStorePoolEnable=false
  ```
  
## 启动

```shell
nohup sh mqnamesrv >/var/log/ns.log &
nohup sh mqbroker -n 127.0.0.1:9876 -c ../conf/broker.conf autoCreateTopicEnable=true > /var/log/mq.log 2>&1 &  
```

## 安装监控控制台

- 下载 [rocketmq-console](https://github.com/apache/rocketmq-externals/tree/master/rocketmq-console) , 修改 `application.properties` 

  ```properties
    rocketmq.config.namesrvAddr=192.168.143.128:9876（ip1:port;ip2:port）
  ```
- 打包

  ```properties
    mvn clean package -Dmaven.test.skip=true
  ```
 - `java -jar target/rocketmq-console-ng-1.0.0.jar` 启动 

## LICENSE

![](LICENSE.png)

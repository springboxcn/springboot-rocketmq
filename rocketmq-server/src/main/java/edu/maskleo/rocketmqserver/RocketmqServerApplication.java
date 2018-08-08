package edu.maskleo.rocketmqserver;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class RocketmqServerApplication {


    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {

        ApplicationContext context = SpringApplication.run(RocketmqServerApplication.class,args);


    }

}

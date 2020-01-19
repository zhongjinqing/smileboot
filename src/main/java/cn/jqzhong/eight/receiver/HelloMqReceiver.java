package cn.jqzhong.eight.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * receive message from hello mq
 * @author zjq
 * @date 2020/1/20 6:47
 */
@Component
@RabbitListener(queues = {"hello"})
public class HelloMqReceiver {

    /**
     * receive mess
     * @param hello mess queue
     */
    @RabbitHandler
    public void receiveMess(String hello){
        System.out.println("Receiver: " + hello);
    }
}

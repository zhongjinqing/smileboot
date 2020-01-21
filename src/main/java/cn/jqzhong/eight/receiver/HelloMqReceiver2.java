package cn.jqzhong.eight.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zjq
 * @date 2020/1/20 7:07
 */
@Component
//@RabbitListener(queues = {"hello"})
public class HelloMqReceiver2 {

    @RabbitHandler
    public void receiveMess(String hello){
        System.out.println("Receiver2: " + hello);
    }
}

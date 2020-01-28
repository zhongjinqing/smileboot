package cn.jqzhong.eight.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zjq
 * @date 2020/1/28 19:57
 */
@Component
public class FanoutReceiver {
    /**
     * receive mess
     * @param mess mess queue
     */
    @RabbitListener(queues = {"fanout.A"})
    @RabbitHandler
    public void receiveMess(String mess){
        System.out.println("queueOneReceiver: " + mess);
    }
    /**
     * receive mess
     * @param mess mess queue
     */
    @RabbitListener(queues = {"fanout.B"})
    @RabbitHandler
    public void receiveMess2(String mess){
        System.out.println("queueTwoReceiver: " + mess);
    }
}

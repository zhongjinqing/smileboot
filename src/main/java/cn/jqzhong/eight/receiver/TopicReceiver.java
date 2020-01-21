package cn.jqzhong.eight.receiver;

import cn.jqzhong.two.bean.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zjq
 * @date 2020/1/21 9:50
 */
@Component
public class TopicReceiver {
    /**
     * receive mess
     * @param mess mess queue
     */
    @RabbitListener(queues = {"topic.message"})
    @RabbitHandler
    public void receiveMess(String mess){
        System.out.println("messageReceiver: " + mess);
    }


    /**
     * receive messages mq mess
     * @param mess mess
     */
    @RabbitListener(queues = {"topic.messages"})
    @RabbitHandler
    public void receiveMessages(String mess){
        System.out.println("messagesReceiver: " + mess);
    }
}

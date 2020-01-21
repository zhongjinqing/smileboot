package cn.jqzhong.eight.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zjq
 * @date 2020/1/21 9:54
 */
@Component
public class TopicSender {

    /**
     * user to send mess
     */
    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * send to router message
     */
    public void sendMessage() {
        rabbitTemplate.convertAndSend("exchange","topic.message","I will go to topic.message");
    }
    /**
     * send to router message
     */
    public void sendMessages() {
        rabbitTemplate.convertAndSend("exchange","topic.messages","I will go to topic.messages");
    }

}

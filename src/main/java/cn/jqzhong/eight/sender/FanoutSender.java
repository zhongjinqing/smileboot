package cn.jqzhong.eight.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author zjq
 * @date 2020/1/28 19:02
 */
@Component
public class FanoutSender {
    /**
     * user to send mess
     */
    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * send to fanout message
     */
    public void sendMessage() {
        rabbitTemplate.convertAndSend("fanout","topic.#","I will go to fanout.messages");
    }
}

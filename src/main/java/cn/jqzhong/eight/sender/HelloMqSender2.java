package cn.jqzhong.eight.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * send message to hello mq
 * @author zjq
 * @date 2020/1/20 6:39
 */
@Component
public class HelloMqSender2 {

    /**
     * amqp:Advanced Message Queuing Protocol，高级消息队列协议
     */
    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * send hello <time> to hello mq
     */
    public void send(String mess) {
        rabbitTemplate.convertAndSend("hello",mess);
    }
}

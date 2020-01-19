package cn.jqzhong.eight.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * config RabbitMq
 * @author zjq
 * @date 2020/1/20 6:32
 */
@Configuration
public class RabbitMqConfig {
    /**
     * create and inject a mq(name: hello)
     * @return mq
     */
    @Bean
    public Queue queue(){
        return new Queue("hello");
    }
}

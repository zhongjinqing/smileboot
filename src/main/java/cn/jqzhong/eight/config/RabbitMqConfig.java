package cn.jqzhong.eight.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * config RabbitMq
 * @author zjq
 * @date 2020/1/20 6:32
 */
@Configuration
public class RabbitMqConfig {
    private static final String MESSAGE = "topic.message";
    private static final String MESSAGES = "topic.messages";
    /**
     * create and inject a mq(name: hello)
     * @return mq
     */
    @Bean
    public Queue queue(){
        return new Queue("hello");
    }
    /**
     * create and inject a mq(name: object)
     * @return mq
     */
    @Bean
    public Queue userQueue(){
        return new Queue("object");
    }
    /**
     * create and inject a mq(name: message)
     * @return mq
     */
    @Bean
    public Queue message(){
        return new Queue(RabbitMqConfig.MESSAGE);
    }
    /**
     * create and inject a mq(name: messages)
     * @return mq
     */
    @Bean
    public Queue messages(){
        return new Queue(RabbitMqConfig.MESSAGES);
    }

    /**
     * create and inject a mq exchang
     * @return
     */
    @Bean("exchange")
    TopicExchange exchange(){
        return new TopicExchange("exchange");
    }

    /**
     * exchange can route to message queue by "topic.message"
     * @param queue message mq
     * @param exchange exchange
     * @return bind
     */
    @Bean
    Binding bindQueueAndExchange(@Qualifier("message") Queue queue,@Qualifier("exchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(RabbitMqConfig.MESSAGE);
    }
    /**
     * exchange can route to message queue by topic.*
     * @param queue message mq
     * @param exchange exchange
     * @return exchange
     */
    @Bean
    Binding bindQueueAndExchange2(@Qualifier("messages") Queue queue,@Qualifier("exchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("topic.#");
    }

}

package cn.jqzhong.eight.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zjq
 * @date 2020/1/28 18:52
 */
@Configuration
public class FanoutConfig {
    /**
     * one queue for receive message
     * @return queue
     */
    @Bean
    public Queue queueOne() {
        return new Queue("fanout.A");
    }
    /**
     * one queue for receive message
     * @return queue
     */
    @Bean
    public Queue queueTwo() {
        return new Queue("fanout.B");
    }

    /**
     * one FanoutExchange for send message
     * @return FanoutExchange
     */
    @Bean("fanout")
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout");
    }

    /**
     * bind queue and fanoutExchange
     * @param queueOne queue
     * @param fanout     fanoutExchange
     * @return Binding
     */
    @Bean
    public Binding bindingExchangeA(Queue queueOne,@Qualifier("fanout") FanoutExchange fanout) {
        return BindingBuilder.bind(queueOne).to(fanout);
    }
    /**
     * bind queue and fanoutExchange
     * @param queueTwo queue
     * @param fanout     fanoutExchange
     * @return Binding
     */
    @Bean
    public Binding bindingExchangeB(Queue queueTwo, FanoutExchange fanout) {
        return BindingBuilder.bind(queueTwo).to(fanout);
    }

}

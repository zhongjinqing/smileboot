# Smileboot
study springboot with pure smlie
# Chapter eight - RabbitMq
- import dependency
```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```
- import config
```
#RabbitMq
spring.rabbitmq.host=127.0.0.1
spring.rabbitmq.port=5672
spring.rabbitmq.username=springboot
spring.rabbitmq.password=springboot
```
- config class
```java
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
```
- sender
```java
@Component()
public class HelloMqSender {

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
```
- receiver
```java
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

@Component
@RabbitListener(queues = {"hello"})
public class HelloMqReceiver2 {
    /**
     * receive mess
     * @param hello mess queue
     */
    @RabbitHandler
    public void receiveMess(String hello){
        System.out.println("Receiver2: " + hello);
    }
}
```
- test
```java
    @RestController
    public class RabbitMqController {
        /**
         * send mess to queue by sender
         */
        @Autowired
        private HelloMqSender mqSender;
    
        /**
         * send mess to queue by sender
         */
        @Autowired
        private HelloMqSender2 mqSender2;
    
        /**
         * send mess
         * @param mess user mess
         * @return actual
         */
        @RequestMapping("mess-oneToOne")
        public String send1(String mess){
            String context = mess + " " + new Date();
            mqSender.send(context);
            return context;
        }
        /**
         * send mess
         * @param mess user mess
         */
        @RequestMapping("mess-oneToMul")
        public void send2(String mess){
            int times = 100;
            for (int i = 0; i < times; i++) {
                mqSender.send(mess +" "+ i);
            }
        }
        /**
         * send mess
         * @param mess user mess
         */
        @RequestMapping("mess-mulToMul")
        public void send3(String mess){
            int times = 100;
            for (int i = 0; i < times; i++) {
                mqSender2.send(mess +" sender2-"+ i);
                mqSender.send(mess +" sender1-"+ i);
            }
        }
    }
```
---
#### ---the rest of  rabbitMq will follow soon
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

### 2020-01-21 continue to study rabbitMq
##### send object
1.  config
```java
    @Bean
    public Queue userQueue(){
        return new Queue("object");
    }
```
2.  sender
```java
    /**
     * send user object
     * @param user user
     */
    public void send(User user) {
        rabbitTemplate.convertAndSend("object",user);
    }
``` 
3. receiver
```java
    -----
    //class annotation
    @RabbitListener(queues = {"object","hello"})
    -----
    /**
     * receive object
     * @param user user
     */
    @RabbitHandler
    public void receiveMess(User user){
        System.out.println("Receiver: " + user);
    }
```
#####Topic Exchange
- config
> cn.jqzhong.eight.config.RabbitMqConfig

- sender
>cn.jqzhong.eight.sender.TopicSender
- receiver
>cn.jqzhong.eight.receiver.TopicReceiver
- test
>cn.jqzhong.eight.controller.RabbitMqController
>@RequestMapping("mess-Topic")

----
#### go home for the Spring Festival 
######Fanout Exchange
- config
>cn.jqzhong.eight.config.FanoutConfig
- sender
>cn.jqzhong.eight.sender.FanoutSender
- receiver
>cn.jqzhong.eight.sender.FanoutReceiver
- test
>cn.jqzhong.eight.controller.RabbitMqController
>@RequestMapping("mess-fanout")

----
####SchedulerTask 
- config
>cn.jqzhong.nine.config.SchedulerTaskConfig
```java
@Configuration
@EnableScheduling
public class SchedulerTaskConfig {

}
```
- task
>cn.jqzhong.nine.tasks.SchedulerTask
```java
@Component
public class SchedulerTask {

    /**
     * taskOne start at
     */
    @Scheduled(cron="* 44 20 * * ?")
    private void taskOne(){
        System.out.println("this is scheduler task runing  in 20:44:00 - 20:45:00");
    }
}
```
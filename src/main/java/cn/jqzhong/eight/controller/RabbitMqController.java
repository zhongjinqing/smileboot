package cn.jqzhong.eight.controller;

import cn.jqzhong.eight.sender.HelloMqSender;
import cn.jqzhong.eight.sender.HelloMqSender2;
import cn.jqzhong.eight.sender.TopicSender;
import cn.jqzhong.two.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * use controller create message
 * @author zjq
 * @date 2020/1/20 6:53
 */
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

    @Autowired
    TopicSender topicSender;
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
    /**
     * send object
     * @param name user name
     */
    @RequestMapping("mess-object")
    public void sendObject(String name){
        User user = new User();
        user.setName(name);
        user.setPassword("12334");
        mqSender.send("Sender object: "+user.toString());
        mqSender.send("Sender string: "+ name);
    }
    @RequestMapping("mess-Topic")
    public void sendTopic(String type){
        if("1".equals(type)){
            for (int i = 0; i < 5; i++) {
                topicSender.sendMessage();
            }
        }else{
            for (int i = 0; i < 5; i++) {
                topicSender.sendMessages();
            }

        }
    }
}

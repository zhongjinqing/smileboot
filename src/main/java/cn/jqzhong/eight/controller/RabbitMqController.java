package cn.jqzhong.eight.controller;

import cn.jqzhong.eight.sender.HelloMqSender;
import cn.jqzhong.eight.sender.HelloMqSender2;
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

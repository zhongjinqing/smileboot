package cn.jqzhong.two.api;

import cn.jqzhong.two.bean.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zjq
 * @date 2020/1/17 22:07
 */
@RestController
@RequestMapping("/test")
public class WebController {

    @RequestMapping("/user")
    public User getUser(){
        User user = new User();
        user.setName("Tom");
        user.setPassword("123456");
        return user;
    }

}

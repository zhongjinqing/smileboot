package cn.jqzhong.three.web;

import cn.jqzhong.two.bean.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author zjq
 * @date 2020/1/17 23:57
 */
@RestController

public class RedisController {

    @RequestMapping("/redis-user")
    @Cacheable(cacheNames="user")
    public User getUser(){
        User user = new User("name", "password", "1", "2","3");
        return user;
    }
    @RequestMapping("/redis-session")
    public String uid(HttpSession session){
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid",uid);
        return session.getId();
    }
}

package cn.jqzhong.shiro.controller;

import cn.jqzhong.six.mapper.UserMapper;
import cn.jqzhong.two.bean.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zjq
 * @date 2020/2/15 22:02
 */
@RestController
@RequestMapping("/shiro")
public class ShiroController {

    @Autowired
    UserMapper userMapper;
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/login")
    public String login(String userName,String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName,password);

        try {
            subject.login(usernamePasswordToken);
        } catch (AuthenticationException e) {
            return "用户名/密码错误";
        }

        return "登录成功";
    }
    @RequestMapping("/user")
    public String user(){
        return "user";
    }
    @RequestMapping("/all")
    public String all(){
        return "all";
    }
    @RequestMapping("/unAuth")
    public String unAuth(){
        return "unAuth";
    }
    @RequestMapping("/add")
    public String add(){
        User user = new User();
        user.setPassword("toy");
        user.setName("Tomcat");
        userMapper.insert(user);
        return "add success";
    }
    @RequestMapping("/delete")
    public String delete(){
        return "delete success";
    }
}

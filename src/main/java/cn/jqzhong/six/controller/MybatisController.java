package cn.jqzhong.six.controller;

import cn.jqzhong.six.mapper.UserMapper;
import cn.jqzhong.six.mapper.UserXmlMapper;
import cn.jqzhong.two.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zjq
 * @date 2020/1/18 13:56
 */
@RestController
public class MybatisController {
   /* @Autowired
    private UserMapper mapper;*/
    @Autowired
    private UserXmlMapper xmlMapper;
   /* @RequestMapping("/user-insert")
    public String insert(User user){
        System.out.println(user);
        mapper.insert(user);
        return "插入成功";
    }
    @RequestMapping("/user-update")
    public String update(User user){
        System.out.println(user);
        mapper.update(user);
        return "更新成功";
    }
    @RequestMapping("/user-delete")
    public String delete(User user){
        System.out.println(user);
        mapper.delete(user.getName());
        return "删除成功";
    }
    @RequestMapping("/user-find")
    public List<User> find(){
        List<User> all = mapper.getAll();
        return all;
    }*/
    @RequestMapping("/user-xmlfind")
    public List<User> findxml(){
        return xmlMapper.getAll();
    }


}

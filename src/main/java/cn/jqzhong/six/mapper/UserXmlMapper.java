package cn.jqzhong.six.mapper;

import cn.jqzhong.two.bean.User;

import java.util.List;

/**
 * @author zjq
 * @date 2020/1/18 14:25
 */
public interface UserXmlMapper {

    public List<User> getAll();

    public User getByName(String name);
}

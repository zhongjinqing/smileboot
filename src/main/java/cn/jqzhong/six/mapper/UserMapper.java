package cn.jqzhong.six.mapper;

import cn.jqzhong.two.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zjq
 * @date 2020/1/18 13:45
 */
public interface UserMapper {

    /**
     * 查找所有
     * @return 所有记录
     */
    @Select("select * from user")
    @Results({
            @Result(property = "name",column = "user_name")
    })
    public List<User> getAll();

    /**
     * 插入一条记录
     * @param user 插入的记录
     */
    @Insert("insert into user(user_name,password)values(#{name},#{password})")
    public void insert(User user);

    @Update("update user set password = #{password} where user_name = #{name}")
    void update(User user);

    /**
     * 删除一条记录
     * @param name 条件
     */
    @Delete("DELETE FROM user WHERE user_name =#{name}")
    void delete(String name);
}

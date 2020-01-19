package cn.jqzhong.seven.mapper;

import cn.jqzhong.seven.bean.Log;
import cn.jqzhong.two.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zjq
 * @date 2020/1/18 13:45
 */
public interface LogMapper {

    /**
     * 查找所有
     * @return 所有记录
     */
    @Select("select * from log")
    /*@Results({
            @Result(property = "name",column = "user_name")
    })*/
    public List<Log> getAll();

}

package cn.jqzhong.seven.controoler;

import cn.jqzhong.seven.bean.Log;
import cn.jqzhong.seven.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zjq
 * @date 2020/1/19 7:11
 */
@RestController
public class LogController {
    @Autowired
    private LogMapper logMapper;

    @RequestMapping("log-find")
    public List<Log> find(){
        return logMapper.getAll();
    }

}

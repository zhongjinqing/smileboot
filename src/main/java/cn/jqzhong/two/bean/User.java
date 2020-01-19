package cn.jqzhong.two.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zjq
 * @date 2020/1/17 22:07
 */
@Data
public class User implements Serializable {
    private String name;
    private String password;
    public User(){}
    public User(String s, String aa, String aa123456, String aa1, String s1) {
        this.name = s;
        this.password = aa;
        System.out.println("无缓存的时候调用");
    }
}

package cn.jqzhong.shiro.config;

import cn.jqzhong.six.mapper.UserXmlMapper;
import cn.jqzhong.two.bean.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zjq
 * @date 2020/2/15 21:43
 */
@Component(value = "userRealm")
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserXmlMapper userXmlMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权逻辑");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermission("user:add");
        return simpleAuthorizationInfo;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证逻辑");
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String username = token.getUsername();
        User user = userXmlMapper.getByName(username);
        System.out.println(user);
        if (user == null){
            return null;
        }

        return new SimpleAuthenticationInfo("",user.getPassword(),"");
    }
}

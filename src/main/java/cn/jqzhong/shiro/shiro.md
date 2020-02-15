- add pom dependency
```xml
<dependency>
    <groupId>org.apache.shiro</groupId>
    <artifactId>shiro-spring</artifactId>
    <version>1.4.0</version>
</dependency>
```
-  add shiro config
```java
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        HashMap<String, String> urlFilter = new HashMap<>(16);
        urlFilter.put("/shiro/login","anon");
        urlFilter.put("/shiro/index","anon");
        urlFilter.put("/shiro/user","authc");
        urlFilter.put("/*/*","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(urlFilter);
        shiroFilterFactoryBean.setLoginUrl("/shiro/login");
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }
```

- user auth
```java
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
```
- add perms

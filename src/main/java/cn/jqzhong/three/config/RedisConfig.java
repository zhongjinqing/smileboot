package cn.jqzhong.three.config;

import org.springframework.cache.annotation.CachingConfigurationSelector;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.lang.reflect.Method;

/**
 * @author zjq
 * @date 2020/1/17 23:46
 */
@Configuration
@EnableCaching
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400*30)
public class RedisConfig extends CachingConfigurationSelector {
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(o.getClass().getName());
                stringBuffer.append(method.getName());
                for (Object object : objects) {
                    stringBuffer.append(object.toString());
                }
                return stringBuffer.toString();
            }
        };
    }
}

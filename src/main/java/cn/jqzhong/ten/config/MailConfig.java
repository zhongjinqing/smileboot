package cn.jqzhong.ten.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author zjq
 * @date 2020/2/4 14:55
 */
@Configuration
@PropertySource("classpath:mail.properties")
public class MailConfig {

}

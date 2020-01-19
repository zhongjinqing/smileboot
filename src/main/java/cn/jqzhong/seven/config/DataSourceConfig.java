package cn.jqzhong.seven.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author zjq
 * @date 2020/1/19 6:56
 */
@Configuration
public class DataSourceConfig {

    /**
     * 主要配置源
     * @return 主
     */
    @Bean(name = "mainDS")
    @ConfigurationProperties(prefix = "spring.datasource.main") // application.properteis中对应属性的前缀
    public DataSource mainDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "otherDS")
    @ConfigurationProperties(prefix = "spring.datasource.other") // application.properteis中对应属性的前缀
    public DataSource otherDataSource() {
        return DataSourceBuilder.create().build();
    }
}

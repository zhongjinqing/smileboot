package cn.jqzhong.seven.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author zjq
 * @date 2020/1/19 7:00
 */
@Configuration
@MapperScans({
        @MapperScan(basePackages = "cn.jqzhong.six.mapper",sqlSessionFactoryRef = "mainSqlSessionFactory"),
        @MapperScan(basePackages = "cn.jqzhong.seven.mapper",sqlSessionFactoryRef = "otherSqlSessionFactory")
})
public class MapperConfig {

    @Autowired
    @Qualifier("mainDS")
    private DataSource mainDS;
    @Autowired
    @Qualifier("otherDS")
    private DataSource otherDS;

    /**
     * 配置文件位置
     * @return
     */
    /*@Bean
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(mainDS);
        return sqlSessionFactoryBean;
    }*/

    @Bean
    public SqlSessionFactory mainSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        //多数据源配置时写在配置文件中不生效
        factoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(
                        "classpath:mybatis/mapper/*.xml"));
        factoryBean.setDataSource(mainDS);
        return  factoryBean.getObject();
    }
    @Bean
    public SqlSessionFactory otherSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(otherDS);
        return  factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate mainSqlSessionTemplate(@Qualifier("mainSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    @Bean
    public SqlSessionTemplate otherSqlSessionTemplate(@Qualifier("otherSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}

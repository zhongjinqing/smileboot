package cn.jqzhong.two.filter;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 1.实现Filter接口，实现init/doFilter/destroy方法
 * 2.加入拦截器到springboot的配置中
 * Configuration注解被扫描到bean返回FilterRegistrationBean
 * @author zjq
 * @date 2020/1/17 22:26
 */
@Configuration
public class WebFilter {

    /**
     *
     * @return
     */
    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    /**
     * 获取自定义的拦截器
     * @return
     */
    @Bean
    public FilterRegistrationBean registrationFilter(){
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new logFilter());
        filter.addUrlPatterns("/*");
        filter.addInitParameter("paramName","paramValue");
        filter.setName("LogFilter");
        filter.setOrder(1);
        return filter;
    }
    class logFilter implements Filter{
        @Override
        public void destroy() {

        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
            System.out.println(httpServletRequest.getRequestURI());
            filterChain.doFilter(servletRequest,servletResponse);
        }

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }
    }
}

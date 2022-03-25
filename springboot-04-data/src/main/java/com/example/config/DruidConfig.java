package com.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    //后台监控
    // 内置Servlet容器时没有web.xml文件，所以使用SpringBoot的注册Servlet方式 ServletRegistrationBean
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    //后台监控 （固定代码）
    @Bean
    public ServletRegistrationBean statViewServlet(){
        //"/druid/**"
        ServletRegistrationBean<StatViewServlet> bean =
                new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");
        //后台需要有人登录，账号密码配置
        HashMap<String,String> initParameters = new HashMap<>();
        //增加配置
        initParameters.put("loginUsername","admin");//登录key 固定的loginUsername
        initParameters.put("loginPassword","123456");//登录key 固定的loginPassword
        //允许谁可以访问
        initParameters.put("allow","");
        //禁止谁能访问  initParameters.put("zhangsan","192.168.11.123"); 表示禁止此ip访问
        //这里根据 key 判断是允许还是禁止，一般key是固定的，自定义的名称禁止

        bean.setInitParameters(initParameters);//设置初始化参数
        return bean;
    }

    //filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        //可以过滤哪些请求
        Map<String,String> initParameters = new HashMap<>();
        //这些东西不进行统计
        initParameters.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParameters);
        return bean;
    }








}









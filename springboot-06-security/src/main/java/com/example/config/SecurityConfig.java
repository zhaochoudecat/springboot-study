package com.example.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 首页所有人可以访问
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        // 开启自动配置的登录功能
        //定制登录页 loginPage
        http.formLogin()
                .loginPage("/")
                //usernameParameter指定前端input的 name
                //.usernameParameter("user").passwordParameter("pwd")
                .loginProcessingUrl("/login");
        //退出
        http.logout().logoutSuccessUrl("/");
        //开启csrf保护 跨站请求伪造
        http.csrf().disable(); // .disable() 关闭
        //记住用户 cooke 默认保存两周
        http.rememberMe().rememberMeParameter("remember");
    }

    //认证
    //密码编码 passwordEncoder
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin").password(new BCryptPasswordEncoder()
                        .encode("123456"))
                .roles("vip2","vip3")
                .and().withUser("guest")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("vip1");
    }
}

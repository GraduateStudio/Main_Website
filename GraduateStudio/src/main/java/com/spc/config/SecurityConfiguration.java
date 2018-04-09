package com.spc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;


@Configuration
//@EnableOAuth2Sso//去认证服务器认证
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * 设定用户访问权限
     * 用户身份可以访问 订单相关API
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/stm/user").hasRole("ADMIN")//用户权限
//                .antMatchers("/stm/wjj.html").hasRole("USER")  //管理员权限
//                .antMatchers("/", "/login**")
//                .permitAll()
//                .anyRequest()
//                .authenticated().and().addFilterAfter(new FilterTest(),AnonymousAuthenticationFilter.class);
        http.authorizeRequests()
                .antMatchers("/stm/user").hasRole("ADMIN")//用户权限
                .antMatchers("/stm/**").hasRole("ADMIN")   //管理员权限
                .antMatchers("/stm/wjj.html").hasRole("ADMIN")  //管理员权限
                .and()
                .formLogin()
                .loginPage("/stm/login").permitAll()
                .and()
                .logout()
                .permitAll().and().addFilterAfter(new FilterTest(),AnonymousAuthenticationFilter.class)
                .csrf().disable();        //暂时禁用CSRF，否则无法提交表单
    }
    /**
     * 添加自定义用户
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN","USER")
                .and()
                .withUser("terry").password("terry").roles("USER")
                .and()
                .withUser("larry").password("larry").roles("USER");
    }

}



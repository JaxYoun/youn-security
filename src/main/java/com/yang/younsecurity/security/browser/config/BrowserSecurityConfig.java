package com.yang.younsecurity.security.browser.config;

import com.yang.younsecurity.security.core.properties.CoreSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author: Yang
 * @date: 2018/9/11 00:58
 * @description: 传统基于浏览器的web应用安全配置
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CoreSecurityProperties coreSecurityProperties;  //不论是默认跳转的登录页还是用户配置的都需要排除

    /**
     * 新版本中，密码必须指定加密工具，密码必须用此工具进行加密和对比，
     * spring-security内置了工具BCryptPasswordEncoder已经很强大了，它可以提供动态salt功能，
     * 即同一个明文在不同时间被加密出的密文都不同，但对比时能保证相同
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 此重载方法是用于配置http认证的方式及相关细节，http认证包括两种
     * 1.http-basic
     * 2.http-formLogin
     * 两种方式的界面稍有区别
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic()  //指定http认证方式为basic登录
        http.formLogin()  //指定http认证方式为表单登录
//                .loginPage("/signIn.html")  //当需要自定义登录页面时就要配置此页面，作为登录入口
                .loginPage("/authentication/require")  //替换掉上面配置的页面，适配不同类型（页面型和数据型）请求返回不同数据的要求
                .loginProcessingUrl("/authentication/form")
                .and()
                .authorizeRequests()  //对请求授权
                .antMatchers("/authentication/require","/signIn.html","/authentication/form", this.coreSecurityProperties.getBrowser().getLoginPage()).permitAll()  //将自定义登录页面排除，不然会导致循环跳转
                .anyRequest()  //任何请求都进行授权
                .authenticated()  //授权方式为登录认证
                .and().csrf().disable();  //自定义登录页面后，需要暂时禁用跨域攻击防护才能成功登录
    }
}

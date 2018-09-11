package com.yang.younsecurity.security.browser.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author: Yang
 * @date: 2018/9/11 22:19
 * @description: 自定义的认证业务类，可以从DB中取出用户信息进行认证，只需实现预定义接口UserDetailsService
 */
@Slf4j
@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(username + "：尝试登录");
        String kkk = passwordEncoder.encode("kkk");
        log.info(kkk);
        User user = new User(
                username,
                kkk,
                true,
                true,
                true,
                true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return user;
    }

}

package com.yang.younsecurity.rest;

import com.yang.younsecurity.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Yang
 * @date: 2018/9/11 00:48
 * @description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getUser")
    public Object getUser() {
        return new User(75, "yang");
    }

    @GetMapping("/getCurrentUser")
    public Object getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/getCurrentPrincipal")
    public Object getCurrentUser(Authentication authentication) {
        return authentication.getPrincipal();
    }

}

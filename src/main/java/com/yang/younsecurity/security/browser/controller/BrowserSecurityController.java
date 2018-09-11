package com.yang.younsecurity.security.browser.controller;

import com.yang.younsecurity.security.browser.support.SimpleResponse;
import com.yang.younsecurity.security.core.properties.CoreSecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Yang
 * @date: 2018/9/11 23:50
 * @description:
 */
@Slf4j
@RestController
public class BrowserSecurityController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    protected CoreSecurityProperties coreSecurityProperties;

    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if(savedRequest != null){
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的url是：" + targetUrl);
            if(StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
                try {
                    this.redirectStrategy.sendRedirect(request, response, this.coreSecurityProperties.getBrowser().getLoginPage());  //登录页面可配置
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        return new SimpleResponse("请认证");
    }
}

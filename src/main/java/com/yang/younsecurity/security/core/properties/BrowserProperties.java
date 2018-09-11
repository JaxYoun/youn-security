package com.yang.younsecurity.security.core.properties;

import lombok.Data;

/**
 * @author: Yang
 * @date: 2018/9/12 00:14
 * @description:
 */
@Data
public class BrowserProperties {

    private String loginPage = "/signIn.html";  //如果在配置文件中未配置自定义登录也就跳到默认登录页

}

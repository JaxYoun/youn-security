package com.yang.younsecurity.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: Yang
 * @date: 2018/9/12 00:13
 * @description:
 */
@Data
@ConfigurationProperties(prefix = "youn.security")
public class CoreSecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

}

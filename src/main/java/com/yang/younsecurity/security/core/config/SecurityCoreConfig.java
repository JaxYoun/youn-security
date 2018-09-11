package com.yang.younsecurity.security.core.config;

import com.yang.younsecurity.security.core.properties.CoreSecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Yang
 * @date: 2018/9/12 00:18
 * @description: 让配置和自定义属性类映射生效
 */
@Configuration
@EnableConfigurationProperties(CoreSecurityProperties.class)
public class SecurityCoreConfig {
}

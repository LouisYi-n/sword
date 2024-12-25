package com.sword.api.tianxing;

import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author louis
 * @version 1.0
 * @date 2024/12/23 18:07
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "tianxing")
public class TianXingConfig {
    private String key;
    private String baseUrl;
    private Map<String, String> urls;
}

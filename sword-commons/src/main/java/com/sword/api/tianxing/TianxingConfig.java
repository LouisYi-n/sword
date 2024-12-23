package com.sword.api.tianxing;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author louis
 * @version 1.0
 * @date 2024/12/23 18:07
 */
@Data
@Component
@ConfigurationProperties(prefix = "tianxing")
public class TianxingConfig {
    private String key;
    private String baseUrl;
    private String rainbowFart;
}

package com.sword;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SwordApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwordApplication.class, args);
    }

    // 为实例设置自定义标签
    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(@Value("${spring.application.name}") String name) {
        return registry -> registry.config().commonTags("application", name);
    }
}

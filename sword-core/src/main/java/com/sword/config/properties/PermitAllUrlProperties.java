package com.sword.config.properties;


import com.sword.annotation.Anonymous;
import org.apache.commons.lang3.RegExUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;
import java.util.regex.Pattern;

/**
 * 设置Anonymous注解允许匿名访问的url
 *
 * @author ruoyi
 */
@Configuration
public class PermitAllUrlProperties implements InitializingBean, ApplicationContextAware {
    private static final Pattern PATTERN = Pattern.compile("\\{(.*?)\\}");

    private ApplicationContext applicationContext;

    private List<String> urls = new ArrayList<>();

    public String ASTERISK = "*";

    @Autowired
    @Qualifier("requestMappingHandlerMapping")
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Override
    public void afterPropertiesSet() {
        /**
         * 在 application.yml 文件中加入了管理端点配置 management.endpoints.web.exposure.include: '*'，
         * 这会暴露所有的 Spring Boot 管理端点。这样做会导致 Spring Boot 自动配置多个 RequestMappingHandlerMapping 实例，
         * 其中一个是常规的 requestMappingHandlerMapping，另一个是用于管理端点的 controllerEndpointHandlerMapping。
         * 正是由于这两个 RequestMappingHandlerMapping 实例的存在，导致了 Spring 在注入 RequestMappingHandlerMapping 时发生了冲突.
         *
         * 通过明确指定要使用的 RequestMappingHandlerMapping 实例，
         * 或者将其中一个设置为主要的 Bean，可以解决 Spring Boot 在管理端点配置时注入 RequestMappingHandlerMapping 发生的冲突。
         */

        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        map.keySet().forEach(info -> {
            HandlerMethod handlerMethod = map.get(info);

            // 获取方法上边的注解 替代path variable 为 *
            Anonymous method = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), Anonymous.class);
            Optional.ofNullable(method).ifPresent(anonymous -> Objects.requireNonNull(info.getPatternsCondition().getPatterns())
                    .forEach(url -> urls.add(RegExUtils.replaceAll(url, PATTERN, ASTERISK))));

            // 获取类上边的注解, 替代path variable 为 *
            Anonymous controller = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), Anonymous.class);
            Optional.ofNullable(controller).ifPresent(anonymous -> Objects.requireNonNull(info.getPatternsCondition().getPatterns())
                    .forEach(url -> urls.add(RegExUtils.replaceAll(url, PATTERN, ASTERISK))));
        });
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.applicationContext = context;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}

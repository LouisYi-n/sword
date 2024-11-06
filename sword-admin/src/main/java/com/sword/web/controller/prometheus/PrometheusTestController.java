package com.sword.web.controller.prometheus;

import io.micrometer.core.instrument.MeterRegistry;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author louis
 * @version 1.0
 * @date 2024/10/31 12:24
 */
@RestController
@RequestMapping("/test/prometheus")
public class PrometheusTestController {

    @Resource
    private MeterRegistry meterRegistry;

    @RequestMapping("/test")
    public String test() {
        // 记录接口调用次数
        incrementCounter("test");
        // 记录接口调用时间
        Long startTime = System.currentTimeMillis();
        // 模拟业务逻辑
        try {
            long millis = RandomUtils.nextLong(1000, 2000);
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        recordTime("test", startTime);
        return "test";
    }

    /**
     * 记录接口调用次数
     * @param methodName 方法名
     */
    private void incrementCounter(String methodName) {
        // 定义指标名称
        String metricName = "method_count";
        meterRegistry.counter(metricName, "methodName", methodName).increment();
    }

    /**
     * 记录接口调用时间
     * @param methodName 方法名
     * @param startTime 方法开始时间
     */
    private void recordTime(String methodName, Long startTime) {
        // 定义指标名称
        String metricName = "method_time";
        meterRegistry.timer(metricName, "methodName", methodName).record(startTime, java.util.concurrent.TimeUnit.MILLISECONDS);
    }
}

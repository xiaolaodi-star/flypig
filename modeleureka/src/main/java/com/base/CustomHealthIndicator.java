package com.base;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {
    //    Spring Boot Actuator 提供了 /actuator/health 端点，
//    该端点可展示应用程序的健康信息，当 MongoDB 异常时
//    /actuator/health 端点的状态会变成 DOWN，由于应用本身确实处于存活状态，
//    但是 MongoDB 的异常会影响某些功能，当请求到达应用之后会发生操作失败的情况。
    @Override
    protected void doHealthCheck(Health.Builder builder){
        builder.down().withDetail("status", false);
    }
}

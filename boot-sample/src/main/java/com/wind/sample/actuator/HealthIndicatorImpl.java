package com.wind.sample.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @author wind
 */
@Component
public class HealthIndicatorImpl implements HealthIndicator {
    @Override
    public Health health() {

        return new Health.Builder().withDetail("usercount", 10) //自定义监控内容
                .withDetail("userstatus", "up").up().build();

    }
}

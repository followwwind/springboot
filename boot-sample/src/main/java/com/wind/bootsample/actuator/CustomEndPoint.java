package com.wind.bootsample.actuator;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * http://localhost:8080/manage/customsystem
 */
@ConfigurationProperties(prefix="endpoints.customsystem")
public class CustomEndPoint extends AbstractEndpoint<Map<String, Object>> {

    public CustomEndPoint() {
        super("customsystem");
    }

    @Override
    public Map<String, Object> invoke() {
        Map<String, Object> result = new HashMap<>();
        Date date = new Date();
        result.put("server_time", date.toString());
        result.put("ms_format", date.getTime());
        return result;
    }
}

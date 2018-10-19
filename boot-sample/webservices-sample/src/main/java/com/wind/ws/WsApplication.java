package com.wind.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wind
 * http://localhost:8181/ws/countries.wsdl
 * 发送curl请求
 * curl --header "content-type: text/xml" -d @request.xml http://localhost:8181/ws
 */
@SpringBootApplication
public class WsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WsApplication.class, args);
    }
}

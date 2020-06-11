package com.song.gateway;

import com.song.gateway.filter.MyGateWayFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public MyGateWayFilter myGateWayFilter() {
        return new MyGateWayFilter();
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
//                .route(p -> p
//                        .path("/get")
//                        .filters(f -> f.addRequestHeader("Hello", "World"))
//                        .uri("http://httpbin.org:80"))
//                .route(p -> p
//                        .host("*.hystrix.com")
//                        .filters(f -> f.hystrix(config -> config
//                                .setName("mycmd")
//                                .setFallbackUri("forward:/fallback")))
//                        .uri("http://httpbin.org:80"))
                // 实现校验token的类似功能
                .route(p -> p
                        .path("/**")
                        .filters(f -> f.filter(myGateWayFilter()))
                        .uri("http://localhost:8003"))

                // 实现根据请求路径,转发到不同域名
                .route(p -> p
                        .path("/query/**")
                        .uri("http://localhost:8003"))
                .route(p -> p
                        .path("/operate/**")
                        .uri("http://localhost:8002"))

                //实现修改请求路径的
                .route(p -> p
                        .path("/test/**")
                        .filters(f -> f.rewritePath("/test/query/test1", "/query/test1"))
                        .uri("http://localhost:8003"))
                .build();
    }
}

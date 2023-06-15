package com.springcamel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.springcamel.services.GreetingService;

@Component
public class GreetingRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:getGreeting")
            .bean(GreetingService.class, "getGreeting(${header.name})")
            .end();

        from("direct:putGreeting")
            .bean(GreetingService.class, "putGreeting(${body})")
            .end();
    }
}

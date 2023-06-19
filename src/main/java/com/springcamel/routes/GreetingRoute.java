package com.springcamel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.springcamel.models.Greeting;
import com.springcamel.services.GreetingService;

@Component
public class GreetingRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        rest("/greeting/")          
            .get("/?name={name}")
                .to("direct:getGreeting")
            .put("/")                    
                .type(Greeting.class)      
                .to("direct:putGreeting");

        from("direct:getGreeting")
            .bean(GreetingService.class, "getGreeting(${header.name})")
            .end();

        from("direct:putGreeting")
            .bean(GreetingService.class, "putGreeting(${body})")
            .end();
    }
}

package com.springcamel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jsonvalidator.JsonValidationException;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import com.springcamel.models.Greeting;
import com.springcamel.processors.InterceptProcessor;
import com.springcamel.processors.ValidationErrorProcessor;
import com.springcamel.services.GreetingService;

@Component
public class GreetingRoute extends RouteBuilder {

    private InterceptProcessor interceptProcessor = new InterceptProcessor();

    @Override
    public void configure() throws Exception {
        onException(JsonValidationException.class)
            .process(new ValidationErrorProcessor())
            .handled(true)
            .end();

        rest("/greeting/")          
            .get("/{name}")
                .to("direct:getGreeting")
            .put("/")                    
                .type(Greeting.class)      
                .to("direct:putGreeting");

        interceptFrom("*")
            .process(interceptProcessor);

        from("direct:getGreeting")
            .bean(GreetingService.class, "getGreeting(${header.name})")
            .end();

        from("direct:putGreeting")
            .marshal().json(JsonLibrary.Jackson, Greeting.class)
            .to("json-validator:greetingSchema.json")
            .unmarshal().json(JsonLibrary.Jackson, Greeting.class)
            .bean(GreetingService.class, "putGreeting(${body})")
            .end();
    }
}

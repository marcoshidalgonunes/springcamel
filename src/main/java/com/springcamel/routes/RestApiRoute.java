package com.springcamel.routes;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.springcamel.components.InterceptProcessor;
import com.springcamel.models.Greeting;

@Component
class RestApiRoute extends RouteBuilder {
    @Value("${server.port}")
    String serverPort;

    @Override
    public void configure() throws Exception  {
        try (CamelContext context = new DefaultCamelContext()) {
            restConfiguration()
                .port(serverPort)
                .enableCORS(true)
                .bindingMode(RestBindingMode.json);  

            intercept().process(new InterceptProcessor());

            rest("/greeting/")          
                .get("/?name={name}")
                    .to("direct:getGreeting")
                .put("/")                    
                    .type(Greeting.class)      
                    .to("direct:putGreeting");
        } 
    }
}

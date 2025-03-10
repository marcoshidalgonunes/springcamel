package com.springcamel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
class RestApiRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception  {
        restConfiguration()
        // and output using pretty print
         .dataFormatProperty("prettyPrint", "true")
         .enableCORS(true)
         // add swagger api-doc out of the box
         .apiContextPath("/api-doc")
             .apiProperty("api.title", "User API")
             .apiProperty("api.version", "1.2.3");
    }
}

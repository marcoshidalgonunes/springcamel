package com.springcamel.routes;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Component;

@Component
class RestApiRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception  {
        try (CamelContext context = new DefaultCamelContext()) {
            restConfiguration()
               // and output using pretty print
                .dataFormatProperty("prettyPrint", "true")
                // add swagger api-doc out of the box
                .contextPath("/api/")
                .apiContextPath("/docs")
                    .apiProperty("api.title", "User API").apiProperty("api.version", "1.2.3")
                    // and enable CORS
                    .apiProperty("cors", "true");                    
        } 
    }
}

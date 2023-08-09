package com.springcamel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.jsonvalidator.JsonValidationException;

public class ValidationErrorProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, JsonValidationException.class);
        if (cause != null) {
            exchange.getIn().setBody(cause.getMessage());
            exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "text/plain");
            exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE,400);
        }        
    }
}

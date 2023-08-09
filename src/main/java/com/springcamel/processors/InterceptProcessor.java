package com.springcamel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class InterceptProcessor implements Processor {
	
	private int count;

    @Override
    public void process(Exchange exchange) throws Exception {
        String uri = exchange.getProperty(Exchange.TO_ENDPOINT, String.class);
        if (!(uri == null || uri.isBlank())) {
            count++;
            System.out.println("'" + uri + "'' route called " + count + " times ");        
        }
    }
}

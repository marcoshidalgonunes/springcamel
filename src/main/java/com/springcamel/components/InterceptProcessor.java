package com.springcamel.components;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class InterceptProcessor implements Processor {
	
	private int count;

    @Override
    public void process(Exchange exchange) throws Exception {
        count++;
        System.out.println("InterceptProcessor processed " + count + " times ");
    }
}

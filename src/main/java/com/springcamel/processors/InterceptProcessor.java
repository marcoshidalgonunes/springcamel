package com.springcamel.processors;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class InterceptProcessor implements Processor {
	
	private Map<String,Integer> map = new HashMap<String, Integer>();

    @Override
    public void process(Exchange exchange) throws Exception {
        String uri = exchange.getProperty(Exchange.TO_ENDPOINT, String.class);
        if (!(uri == null || uri.isBlank())) {
            if (!map.containsKey(uri)) {
                map.put(uri, 0);
            }
            int count = map.get(uri) + 1;
            map.replace(uri, count);
            System.out.println("'" + uri + "' route called " + count + " times ");        
        }
    }
}

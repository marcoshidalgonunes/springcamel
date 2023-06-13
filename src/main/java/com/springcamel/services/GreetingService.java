package com.springcamel.services;

import com.springcamel.models.Greeting;

public class GreetingService {

    private long count;

	private static final String template = "Hello, %s!";

	public Greeting getGreeting(String name) {
		return new Greeting(++count, String.format(template, name));
	}    

    public Greeting postGreeting(Greeting greeting) {
		return new Greeting(greeting.id() + 1, String.format(template, greeting.content()));
	}
}

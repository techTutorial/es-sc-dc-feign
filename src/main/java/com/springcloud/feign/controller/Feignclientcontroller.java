package com.springcloud.feign.controller;

import static java.lang.System.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

// http://localhost:9191/feign/greet2/en
@RestController
@RequestMapping(value= "/feign")
public class Feignclientcontroller {

	@Autowired
	Greetingsclient greetingsfeignclient;

	// it provides one more level of request mapping
	@GetMapping(value="/greet2/{localeId}", produces= MediaType.APPLICATION_JSON_VALUE)
	@HystrixCommand(fallbackMethod= "defaultGreetingsResponse")
	public ResponseEntity<String> getGreetings(@PathVariable(name= "localeId") String langCode) {
		out.println("Declarative approach of Feign Client: Data is fetched from another micro-service hosted on port no 8181");
		String greetMsg = greetingsfeignclient.getGreetings(langCode);
		out.println("Locale Language: " + langCode + ", Greeting Message: " + greetMsg);
		return new ResponseEntity<String>(greetMsg, HttpStatus.OK);
	}

	// When we define a fallback-method, the fallback-method must match the same parameters of the method 
	// where you define the Hystrix Command using the hystrix-command annotation.
	public ResponseEntity<String> defaultGreetingsResponse(String err) {
		err = "Fallback error response as underlying microservice is down.";
		return new ResponseEntity<String>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}

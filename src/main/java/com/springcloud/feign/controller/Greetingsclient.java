package com.springcloud.feign.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// same as application name of dependent microservice
@FeignClient(name= "discovery-client")
public interface Greetingsclient {

	// request mapping URL is same as mapping URL of dependent microservice
	@GetMapping(value= "/greet/msg/{localeId}")
	// Interface method has dependency on another microservice
	public String getGreetings(@PathVariable(name= "localeId") String langCode);

}

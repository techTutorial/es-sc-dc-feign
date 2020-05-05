package example.spring.cloud.feign.service;

import static java.lang.System.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import example.spring.cloud.feign.client.GreetingsFeignClient;

@Service
public class GreetingsFeignClientService {

	@Autowired
	GreetingsFeignClient gfc;

	@HystrixCommand(fallbackMethod= "defaultGreetingsFallback")
	public String getGreetings(String langCode) {
		String greetMsg = gfc.getGreetings(langCode);
		out.println("Declarative approach of Feign Client: Data is fetched from another micro-service hosted on port no 8181");
		out.println("Locale Language: " + langCode + ", Greeting Message: " + greetMsg);
		return greetMsg;
	}

	// When we define a fallback-method, the fallback-method must match the same parameters of the method 
	// where you define the Hystrix Command using the hystrix-command annotation.
	public String defaultGreetingsFallback(String langCode) {
		return langCode + ": Fallback error response as underlying microservice is down.";
	}
	
}

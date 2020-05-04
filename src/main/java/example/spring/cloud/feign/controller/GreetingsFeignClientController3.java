package example.spring.cloud.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.spring.cloud.feign.client.GreetingsFeignClient3;

// http://localhost:9191/feign3/greet3/en
@RestController
@RequestMapping(value= "/feign3")
public class GreetingsFeignClientController3 {

	@Autowired
	GreetingsFeignClient3 gfcr3;	
	
	@GetMapping(value="/greet3/{localeId}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getGreetings3(@PathVariable(name= "localeId") String langCode) {
		String greetMsg = gfcr3.getGreetings3(langCode);
		if(greetMsg.contains("Fallback")) {
			return new ResponseEntity<String>(greetMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(greetMsg, HttpStatus.OK);
	}
	
}

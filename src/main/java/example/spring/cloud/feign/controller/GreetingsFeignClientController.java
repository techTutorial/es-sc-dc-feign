package example.spring.cloud.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.spring.cloud.feign.service.GreetingsFeignClientService;

// http://localhost:9191/feign/greet2/en
@RestController
@RequestMapping(value= "/feign")
public class GreetingsFeignClientController {

	@Autowired
	GreetingsFeignClientService gfcs;
	
	// it provides one more level of request mapping
	@GetMapping(value="/greet2/{localeId}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getGreetings(Model model, @PathVariable(name= "localeId") String langCode) {
		String greetMsg = gfcs.getGreetings(langCode);
		
		if(greetMsg.contains("Fallback")) {
			return new ResponseEntity<String>(greetMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		model.addAttribute("greeting", greetMsg);
		
		return new ResponseEntity<String>(greetMsg, HttpStatus.OK);
	}
	
}

package example.spring.cloud.feign.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public interface GreetingsFeignClientService3 {
	
    @RequestMapping("/greet/msg/{localeId}")
    public String getGreetings3(@PathVariable("localeId") String langCode);
    
}

package example.spring.cloud.feign.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public interface GreetingController3 {
    @RequestMapping("/greet/msg/{username}")
    public String getGreetings3(@PathVariable("username") String username);
    //public String getGreetings(@PathVariable(name= "localeId") String langCode);
}

package example.spring.cloud.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "discovery-client", url = "http://localhost:8181", fallback = GreetingClient3.GreetingClientFallback3.class)
public interface GreetingClient3 extends GreetingController3 {
	
    @Component
    public static class GreetingClientFallback3 implements GreetingClient3 {
    	
        @Override
        public String getGreetings3(@PathVariable("username") String username) {
            return "Hello User!";
        }
        
    }
    
}
